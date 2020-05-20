import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    contents: [
      {
        age: 0,
        score: 0,
        comment: ''
      }
    ],
    load: false,
    loaded: true
  },
  mutations: {
    addContentMutation (state, content) {
      state.contents.push(content)
    }
  },
  actions: {
    addContent ({ commit }, content) {
      commit('addContentMutation', content)
    }
  }
}
