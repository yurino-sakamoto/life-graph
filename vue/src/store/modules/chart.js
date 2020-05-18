import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  namespaced: true,
  state: {
    contents: [
      {
        age: 0,
        score: 30,
        comment: 'あいうえお'
      },
      {
        age: 5,
        score: 80,
        comment: 'あいうえお'
      },
      {
        age: 10,
        score: -10,
        comment: 'あいうえお'
      }
    ],
    load: false,
    loaded: false
  },
  mutations: {
    addContentMutation (state, content) { // 第二引数の、s要らん
      state.contents.push(content)
    }
  },
  actions: {
    addContent ({ commit }, content) {
      commit('addContentMutation', content)
    }
  }
})
