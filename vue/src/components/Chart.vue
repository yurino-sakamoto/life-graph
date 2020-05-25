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
            label: '',
            backgroundColor: 'rgba(254,95,82,1)',
            data: [],
            fillColor: 'rgba(254,95,82,0.6)',
            strokeColor: 'rgba(254,95,82,1)',
            pointColor: 'rgba(254,95,82,1)',
            pointHighlightFill: 'rgba(86,84,82,1)', // /pointHighlightStroke: 'yellow',
            borderWidth: 1,
            fill: false,
            lineTension: 0.4
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
            innerHtml += '<tr><th>' + `年齢：${age}才` + '</th></tr>'
            innerHtml += '</thead><tbody>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 2px'
              var span = '<span style="' + style + '"></span>'
              if (comment.comment) {
                innerHtml += '<tr><td>' + span + '満足度:' + body + '%' + '</td></tr>' + 'コメント:' + comment.comment
              } else {
                innerHtml += '<tr><td>' + span + '満足度:' + body + '%' + '</td></tr>'
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }
        var position = this._chart.canvas.getBoundingClientRect()
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        tooltipEl.style.backgroundcolor = 'rgba(228,232,239,1)'
        tooltipEl.style.fontFamily = tooltipModel._bodyFontFamily
        tooltipEl.style.fontSize = tooltipModel._bodyFontSize
        tooltipEl.style.fontStyle = tooltipModel._bodyFontStyle
        tooltipEl.style.padding = tooltipModel.yPadding + 'px ' + tooltipModel.xPadding + 'px'
        tooltipEl.style.pointerEvents = 'none'
      }
    }
  }
}
</script>
