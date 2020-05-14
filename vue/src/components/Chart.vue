<script>
import { Line } from 'vue-chartjs'

export default {
  extends: Line,
  mounted () {
    this.renderChart({
      labels: ['0', '5', '10', '15', '20', '25', '30'],
      datasets: [
        {
          label: '人生グラフ',
          backgroundColor: '#f565452',
          data: [40, 20, 10, 80, 0, 60, 100],
          fillColor: 'rgba(165,42,42,0.65)', // 線から下端までを塗りつぶす色
          strokeColor: 'rgba(165,42,42,1)', //  折れ線の色
          pointColor: 'rgba(165,42,42,1)', //  ドットの塗りつぶし色
          pointHighlightFill: 'yellow', //  マウスが載った際のドットの塗りつぶし色/             pointHighlightStroke: 'yellow', // マウスが載った際のドットの枠線色
          //  label:ここにスコアが入るからlabelの中は空にする
          borderWidth: 1,
          fill: false,
          lineTension: 0.4
        }
      ]
    }, { responsive: true, maintainAspectRatio: false })
  }
}
// export default {
//   name: 'Chart',
//   extends: Line,
//   data () {
//     return {
//       data: {
//         labels: [0, 5, 10, 15, 20],
//         datasets: [
//           {
//             data: [
//               70, 70, -10, 60, 10
//             ],
//             fillColor: 'rgba(165,42,42,0.65)', // 線から下端までを塗りつぶす色
//             strokeColor: 'rgba(165,42,42,1)', //  折れ線の色
//             pointColor: 'rgba(165,42,42,1)', //  ドットの塗りつぶし色
//             pointHighlightFill: 'yellow', //  マウスが載った際のドットの塗りつぶし色
//             pointHighlightStroke: 'yellow', // マウスが載った際のドットの枠線色
//             //  label:ここにスコアが入るからlabelの中は空にする
//             borderWidth: 1,
//             fill: false,
//             lineTension: 0.4
//           }
//         ]
//       },
//       options: {
//         tooltip: {
//           callback: {
//           }
//         },
//         //  凡例消す
//         legend: false,
//         scale: {
//           xAxes: [{
//             gridLines: {
//               //  縦線消す
//               display: false,
//               labelString: '年齢'
//             }
//           }],
//           yAxes: [{
//             gridLines: {
//               //  zerolineを残すため
//               display: false,
//               zeroLineColor: 'rgba(86,84,82,1)'
//             },
//             ticks: {
//               beginAtZero: true,
//               suggestedMax: 100,
//               suggestedMin: -100,
//               sampleSize: 5
//             }
//           }]
//         }
//       }
//     }
//   },
//   mounted () {
//     this.renderChart(this.data, this.options)
//   }
// }
</script>
