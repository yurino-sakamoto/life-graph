<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Lifechart',
  extends: Line,
  props: {
    referenceMode: { default: false, type: Boolean, required: false }
  },
  data () {
    return {
      data: {
        labels: [],
        datasets: [
          {
            data: [],
            label: '',
            fillColor: '#00FFD0', // 線から下端までを塗りつぶす色
            borderColor: '#00FFD0', //  折れ線の色
            pointColor: 'rgba(0,255,208,0.1)', //  ドットの塗りつぶし色
            pointBackgroundColor: 'rgba(0,255,208,0.1)',
            backgroundColor: 'rgba(0,255,208,0.1)',
            borderWidth: 2.4,
            pointBorderWidth: 1.2,
            pointRadius: 4,
            pointBorderColor: '#00FFD0',
            hoverRadius: 12,
            lineTension: 0,
            fill: 'start'
          }
        ]
      },
      options: {
        maintainAspectRatio: false,
        tooltips: {
          enabled: false,
          custom: []
        },
        legend: false,
        scales: {
          xAxes: [{
            gridLines: {
              display: true
            },
            ticks: {
              beginAtZero: true,
              max: 100,
              min: 0,
              stepsize: 1
            }
          }],
          yAxes: [{
            gridLines: {
              display: true
            },
            ticks: {
              beginAtZero: true,
              max: 100,
              min: -100,
              stepsize: 1
            }
          }]
        }
      },
      responsive: true,
      contents: []
    }
  },
  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    }
  },
  watch: {
    loaded: function () {
      this.createChart()
    }
  },
  mounted () {
    this.contents = []
    this.createChart()
  },
  methods: {
    createChart () {
      this.sortContents()
      this.setAge()
      this.setScore()
      this.setComment()
      this.renderChart(this.data, this.options)
    },
    sortContents () {
      this.contents = this.referenceMode ? this.$store.state.chart.otherMemberContents.slice() : this.$store.state.chart.contents.slice()
      this.contents.sort(this.compareAge)
    },
    compareAge (a, b) {
      return a.age - b.age
    },
    setAge () {
      this.data.labels = this.contents.map((content) => {
        return content.age
      })
    },
    setScore () {
      this.data.datasets[0].data = this.contents.map((content) => {
        return content.score
      })
    },
    setComment () {
      const comments = this.contents.map((content) => {
        return { age: content.age, comment: content.comment }
      })
      const comment = []
      this.contents.map((content) => {
        comment.push(content.comment)
      })
      // tooltip設定
      // https://misc.0o0o.org/chartjs-doc-ja/configuration/tooltip.html#%E5%A4%96%E9%83%A8%E3%82%AB%E3%82%B9%E3%82%BF%E3%83%A0%E3%83%84%E3%83%BC%E3%83%AB%E3%83%81%E3%83%83%E3%83%97
      this.options.tooltips.custom = function (tooltipModel) {
        var tooltipEl = document.getElementById('chartjs-tooltip')
        if (!tooltipEl) {
          tooltipEl = document.createElement('div')
          tooltipEl.id = 'chartjs-tooltip'
          tooltipEl.innerHTML = '<table></table>'
          document.body.appendChild(tooltipEl)
        }
        tooltipEl.style.opacity = 1

        if (tooltipModel.opacity === 0) {
          tooltipEl.style.opacity = 0
          return
        }
        tooltipEl.classList.remove('above', 'below', 'no-transform')
        if (tooltipModel.yAlign) {
          tooltipEl.classList.add(tooltipModel.yAlign)
        } else {
          tooltipEl.classList.add('no-transform')
        }
        function getBody (bodyItem) {
          return bodyItem.lines
        }
        if (tooltipModel.body) {
          var titleLines = tooltipModel.title
          var bodyLines = tooltipModel.body.map(getBody)
          var innerHtml = '<thead>'
          titleLines.forEach(function (age) {
            var comment = comments.find(contents => parseInt(contents.age) === parseInt(age))
            innerHtml += '<tr><td>' + `${age}歳` + '</td></tr>'
            innerHtml += '</thead><tbody>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 2px'
              var span = '<span style="' + style + '"></span>'
              if (comment.comment) {
                innerHtml += '<tr><td>' + span + body + '点' + '</td></tr>' + '<tr><td>' + comment.comment + '</td></tr>'
              } else {
                innerHtml += '<tr><td>' + span + body + '点' + '</td></tr>'
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }
        var position = this._chart.canvas.getBoundingClientRect()
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.opacity = 1
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        tooltipEl.style.padding = '15' + 'px'
        tooltipEl.style.fontFamily = 'Roboto'
        tooltipEl.style.color = '#565452'
        tooltipEl.style.fontSize = '16px'
        tooltipEl.style.fontStyle = 'Roboto'
        tooltipEl.style.pointerEvents = 'none'
        tooltipEl.style.borderRadius = '10' + 'px'
        tooltipEl.style.width = '200' + 'px'
      }
    }
  }
}
</script>
