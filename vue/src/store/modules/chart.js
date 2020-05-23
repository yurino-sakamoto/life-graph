import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    contents: [
      {
        age: 10,
        score: 90,
        comment: 'あああ'
      }
    ],
    load: false,
    loaded: true
  },
  mutations: {
    addContentMutation (state, payload) {
      state.contents = payload
    },
    addDataMutation (state, contents) {
      state.contents = contents
    }
  },
  actions: {
    async addContent ({ commit }, userId) { // 引数としてuserId（auth.js）
      const url = '/api/reference/' + userId
      await axios.get(url).then(res => commit('addContentMutation', res.data))
        .catch(err => err)
    },
    addData ({ commit }, contents) {
      commit('addDataMutation', contents)
    },
    async editContent ({ commit }, apiContents) {
      const url = 'api/life-graphs'
      axios.post(url, apiContents).then(res => commit(res.data))
        .catch(err => err)
    }
  }
}
