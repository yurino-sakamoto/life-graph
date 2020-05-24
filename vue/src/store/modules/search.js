import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
// 手順（）
// ①ユーザーが入力した情報を箱に詰める()
// ②箱を引数としてsearchAPIに渡す
// ③searchAPIが動き、stateの中に情報が詰まる
// ④情報を取り出し、検索結果画面に詰める

export default {
  namespaced: true,
  state: {
    searchItems: [],
    error: ''
  },
  mutations: {
    searchAPI (state, payload) {
      state.searchItems = payload
    },
    error (state, err) {
      state.error = err
    },
    clearError (state) {
      state.error = ''
    }
  },
  actions: {
    searchAPI ({ commit }, data) {
      // console.log('actionにきたよー！')
      const url = '/api/search'
      axios.get(url, data).then(res => commit('searchAPI', res.data))
      // console.log('API終了')
    },
    deleteGraphData ({ commit }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.delete(url)
        .catch(err => commit('error', err))
    }
  }
}
//       try {
//         const res = await Promise.all([
//           axios.get('/likename'),
//           axios.get('/startDate'),
//           axios.get('/finishDate')
//         ])
//         return commit('setSearchGraphs', res.data)
//       } catch (err) {

//         return err
//       }
//     }
//   }
// }
