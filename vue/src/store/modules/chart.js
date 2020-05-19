import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
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
      },
      {
        age: 15,
        score: 40,
        comment: 'あいうえお'
      },
      {
        age: 20,
        score: -80,
        comment: 'あいうえお'
      },
      {
        age: 25,
        score: 100,
        comment: 'あいうえお'
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
