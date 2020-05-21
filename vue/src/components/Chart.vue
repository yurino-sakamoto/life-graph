<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Lifechart',
  extends: Line,
  data () {
    return {
      data: {
        labels: ['0', '5', '10', '15', '20', '25', '30'],
        datasets: [
          {
            label: '人生グラフ',
            backgroundColor: 'rgba(254,95,82,1)',
            data: [],
            fillColor: 'rgba(254,95,82,0.6)', // 線から下端までを塗りつぶす色
            strokeColor: 'rgba(254,95,82,1)', //  折れ線の色
            pointColor: 'rgba(254,95,82,1)', //  ドットの塗りつぶし色
            pointHighlightFill: 'rgba(86,84,82,1)', // マウスが載った際のドットの塗りつぶし色/pointHighlightStroke: 'yellow', // マウスが載った際のドットの枠線色
            // label:ここにスコアが入るからlabelの中は空にする
            borderWidth: 1,
            fill: false,
            lineTension: 0.4
          }
        ]
      },
      options: {
        maintainAspectRatio: false,
        tooltip: {
          enabled: false,
          custom: [] // 下で定義
          // 定型文のfunction()はデータの中で使えないから下の方で出力
        },
        legend: false, // 凡例
        scales: {
          xAxes: [{
            gridLines: {
              //  縦線消す
              display: true,
              labelString: '年齢',
              fontColor: 'rgba(86,84,82,1)'
            },
            ticks: {
              beginAtZero: true,
              max: 25,
              min: 0,
              stepsize: 1
            }
          }],
          yAxes: [{
            gridLines: {
              //  zerolineを残すため
              display: true,
              labelString: '満足度'
            },
            ticks: {
              beginAtZero: true,
              max: 100,
              min: -100,
              stepsize: 1,
              fontColor: 'rgba(86,84,82,1)'
            }
          }]
        }
      },
      responsive: true
    }
  },
  async  mounted () {
    const userId = this.$store.state.auth.userId // 変数userIdを定義。ログイン情報。省略
    await this.$store.dispatch('chart/addContent', userId)
    this.setAge()
    this.setScore()
    this.setComment()
    this.renderChart(this.data, this.options)
    // console.log(this.data)
    // console.log(this.options)
  },
  methods: { // 処理を埋める
    setAge () { // Age=.js age=vue
      const age = []
      this.$store.state.chart.contents.map((Year) => { // forEachではなくmap
        age.push(Year.age)// ageにAgeの中のageを全てプッシュ
      })
      this.data.datasets[0].data = age
    },
    setScore () {
      const score = []
      this.$store.state.chart.contents.map((Score) => { // contentsのp中のidの中を回せば良い？
        score.push(Score.score)
      })
      this.data.datasets[0].data = score
    },
    setComment () {
      const comment = []
      this.$store.state.chart.contents.map((Comment) => {
        comment.push(Comment.comment)
      })
      // tooltip設定
      // https://misc.0o0o.org/chartjs-doc-ja/configuration/tooltip.html#%E5%A4%96%E9%83%A8%E3%82%AB%E3%82%B9%E3%82%BF%E3%83%A0%E3%83%84%E3%83%BC%E3%83%AB%E3%83%81%E3%83%83%E3%83%97
      this.options.tooltip.custom = function (tooltipModel) { // .~.~・・・名前空間
        // ツールチップ要素
        var tooltipEl = document.getElementById('chartjs-tooltip')
        // 最初のレンダリング時に要素を作成する
        if (!tooltipEl) {
          tooltipEl = document.createElement('div')
          tooltipEl.id = 'chartjs-tooltip'
          tooltipEl.innerHTML = '<table></table>'
          document.body.appendChild(tooltipEl)
        }
        // ツールチップがない場合は非表示にします
        if (tooltipModel.opacity === 0) {
          tooltipEl.style.opacity = 0
          return
        }
        // キャレット(ツールチップが指し示すもの)の位置を設定する
        tooltipEl.classList.remove('above', 'below', 'no-transform')
        if (tooltipModel.yAlign) {
          tooltipEl.classList.add(tooltipModel.yAlign)
        } else {
          tooltipEl.classList.add('no-transform')
        }
        function getBody (bodyItem) {
          return bodyItem.lines
        }
        // テキストを設定する
        if (tooltipModel.body) {
          var titleLines = tooltipModel.title
          var bodyLines = tooltipModel.body.map(getBody)
          var com = comment
          var innerHtml = '<thead>'
          titleLines.forEach(function (age) {
            var comNum = age - 0// ここ修正必須
            innerHtml += '<tr><th>' + age + '</th></tr>'
            innerHtml += '</thead><tbody>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 2px'
              var span = '<span style="' + style + '"></span>'
              if (com[comNum] !== null) {
                innerHtml += '<tr><td>' + span + '満足度:' + body + '%' + '</td></tr>' + 'コメント:' + com[comNum]
              } else {
                innerHtml += '<tr><td>' + span + '満足度:' + body + '%' + '</td></tr>'
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }
        // 'this'はツールチップ全体
        var position = this._chart.canvas.getBoundingClientRect()
        // 表示、配置、およびフォントスタイルの設定
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
