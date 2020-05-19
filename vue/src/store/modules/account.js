// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)
// export default {
//   state: {
//     acountInfo: []
//   },
//   getters: {
//     selectName (state) { // selectNameという処理を定義。
//       const acountName = [] // acountNameという配列を定義。
//       state.acountInfo[0].forEach((filter) => { // state/chartScore0番目の配列/skillsの配列全てに次の処理を実行。処理filterを定義。
//         acountName.push(filter.name) // 定義した frontScoreAllay に 処理filter で取得したscoreの値をプッシュする処理。
//       })
//       return acountName // 上の処理結果を返す。acountNameの値が決まる。
//     },
//     selectAuthority (state) {
//       const acountAuthority = []
//       state.acountInfo[0].forEach((filter) => {
//         acountAuthority.push(filter.authority)
//       })
//       return acountAuthority
//     }
//   },
//   mutations: {
//     setAcountInfo (state, payload) {
//       state.acountInfo = payload.acountInfo
//     }
//   },
//   actions: {
//     async accountAction ({ commit }) {
//       const acountInfo = [];
//       const Array = await axios.get('/api/auth/accountReference').then(res
//       => commit('setAcountInfo', res.data))
//     }
//   }
// }
