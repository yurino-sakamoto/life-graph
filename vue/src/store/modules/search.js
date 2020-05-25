import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    searchItems: [],
    error: '',
    showResult: false
  },
  mutations: {
    searchAPI (state, payload) {
      state.searchItems = payload
      state.showResult = true
    },
    error (state, err) {
      state.error = err
    },
    clearError (state) {
      state.error = ''
    },
    loadFalse (state) {
      state.showResult = false
    }
  },
  actions: {
    async searchAPI ({ commit }, data) {
      const url = '/api/life-graphs'
      await axios.get(url, data).then(res => commit('searchAPI', res.data))
    },
    deleteGraphData ({ commit }, parentId) {
      const url = '/api/life-graphs/' + parentId
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
