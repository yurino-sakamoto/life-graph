import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    contents: [
      {
        age: 2,
        score: 70,
        comment: '反抗期'
      },
      {
        age: 18,
        score: 100,
        comment: '受験成功'
      },
      {
        age: 25,
        score: 100,
        comment: '結婚'
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
      const url = '/life-graphs'
      axios.post(url, apiContents).then(res => commit(res.data))
        .catch(err => err)
    }
  }
}
