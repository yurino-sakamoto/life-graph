<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Lifechart',
  extends: Line,
  props: {
    referenceMode: { default: false, type: Boolean, required: false }
  }, // デフォルトで値なし。データ型は数値。requiredでfalseのこともある。reference.vueのchartに定義。
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
            lineTension: 0.3,
            fill: 'start'
          }
        ]
      },
      options: {
        maintainAspectRatio: false,
        tooltips: {
          enabled: false,
          custom: [] // 下で定義
          // 定型文のfunction()はデータの中で使えないから下の方で出力
        },
        legend: false, // 凡例
        scales: {
          xAxes: [{
            gridLines: {
              //  縦線消す
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
              //  zerolineを残すため
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
      contents: [] // 【並び替え】並び替えられたcontentsのコピーがここに入る。ここからsetメソッドで取得する。
    }
  },
  computed: {
    loaded () { // mountedのaddContentアクションでloadedが変化。このタイミングでチャートを描画したい。（mountedで描画するとデータがくる前に描画されてしまうため。）
      return this.$store.state.chart.loaded
    }
  },
  watch: { // computedでの変化を監視し、チャート描画のタイミングを合わせる。
    loaded: function () {
      this.createChart()
    }
  },
  mounted () {
    this.contents = [] // contents初期化
    this.createChart() // TOP画面用。登録していないユーザーに空のグラフを描画。
  },
  methods: {
    createChart () {
      this.sortContents() // 【並び替え】
      this.setAge()
      this.setScore()
      this.setComment()
      this.renderChart(this.data, this.options)
    },
    sortContents () { // 【並び替え】「sliceメソッド」で「contents」のコピーを整形してる。「=」でも可能だが、mutation以外でstateを整形するのはエラーの元。
      this.contents = this.referenceMode ? this.$store.state.chart.otherMemberContents.slice() : this.$store.state.chart.contents.slice()
      this.contents.sort(this.compareAge) // compareAgeを引数に持つsortメソッドでcontentsを・・
    },
    compareAge (a, b) { // 【並び替え】[0]と[1]のageの値を比較。「a.age - b.age」なら降順。「b.age - a.age」なら昇順。
      return a.age - b.age
    },
    setAge () {
      this.data.labels = this.contents.map((content) => { // 【並び替え】「this.contents」は67行目の「contents」
        return content.age
      })
    },
    setScore () {
      this.data.datasets[0].data = this.contents.map((content) => {
        return content.score
      })
    },
    setComment () {
    // 用意したcommentsという箱に、contents.ageという配列を持ったageと、contents.commentという配列を持った
      // commentをObjectとしてpushする
      const comments = this.contents.map((content) => {
        return { age: content.age, comment: content.comment }
      })
      const comment = []
      this.contents.map((content) => {
        comment.push(content.comment)
      })
      // tooltip設定
      // https://misc.0o0o.org/chartjs-doc-ja/configuration/tooltip.html#%E5%A4%96%E9%83%A8%E3%82%AB%E3%82%B9%E3%82%BF%E3%83%A0%E3%83%84%E3%83%BC%E3%83%AB%E3%83%81%E3%83%83%E3%83%97
      this.options.tooltips.custom = function (tooltipModel) { // .~.~・・・名前空間
        // ツールチップ要素
        var tooltipEl = document.getElementById('chartjs-tooltip')
        // 最初のレンダリング時に要素を作成する
        if (!tooltipEl) {
          tooltipEl = document.createElement('div')
          tooltipEl.id = 'chartjs-tooltip'
          tooltipEl.innerHTML = '<table></table>'
          document.body.appendChild(tooltipEl)
        }

        // opacityを1に戻す
        tooltipEl.style.opacity = 1

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
        // 'this'はツールチップ全体
        var position = this._chart.canvas.getBoundingClientRect()
        // 表示、配置、およびフォントスタイルの設定
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.opacity = 1
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        tooltipEl.style.padding = '8' + 'px' + '8' + 'px'
        tooltipEl.style.backgroundColor = '#565452'
        tooltipEl.style.fontFamily = 'Roboto'
        tooltipEl.style.color = '#FFF'
        tooltipEl.style.fontSize = '16px'
        tooltipEl.style.fontStyle = 'Roboto'
        tooltipEl.style.pointerEvents = 'none'
      }
    }
  }
}
</script>
