// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)
// // 手順（）
// // ①ユーザーが入力した情報を箱に詰める()
// // ②箱を引数としてsearchAPIに渡す
// // ③searchAPIが動き、stateの中に情報が詰まる
// // ④情報を取り出し、検索結果画面に詰める

// export default {
//   state: {
//     username: '',
//     user_id: '',
//     updated_at: '',
//     created_at: ''
//   },
//   mutations: {
//     setGraphData (state, data) {
//       state.username = data.username
//       state.user_id = data.user_id
//       state.updated_at = data.updated_at
//       state.created_at = data.created_at
//     },
//     deleteGraphData(state) {
//       tate.username = '',
//       state.user_id = '',
//       state.updated_at = '',
//       state.created_at = ''
//     }
//   },
//   actions: {
//     searchAPI ({ commit }, data) {
//       const url = '/api/search'
//       axios.post(url, data).then(res => commit('searchAPI', res.data))
//     },
//     deleteGraphData ({ commit }, userId) {
//       const url = '/api/life-graphs/' + userId
//       axios.delete(url).then(res => commit('deleteGraphData', res.data))
//     }
//   }
// }
// //       try {
// //         const res = await Promise.all([
// //           axios.get('/likename'),
// //           axios.get('/startDate'),
// //           axios.get('/updateDate')
// //         ])
// //         return commit('setSearchGraphs', res.data)
// //       } catch (err) {

// //         return err
// //       }
// //     }
// //   }

// // methods: {
// //   getAddress() {
// //     this.$store.commit("setSearchGraphs", )
// //     this.$store.dispatch("SearchGraphInfos");
// //   }
// // }
