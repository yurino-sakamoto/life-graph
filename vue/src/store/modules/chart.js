import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    contents: [],
    load: false,
    loaded: true
  },
  mutations: {
    addContentMutation (state, payload) {
      state.contents = payload
    },
    resetContents (state) {
      state.contents = [] // destroyedでstateを空に
    }
  },
  actions: {
    async addContent ({ commit }, userId) { // 引数としてuserId（auth.js）
      const url = '/api/reference/' + userId
      await axios.get(url).then(res => commit('addContentMutation', res.data))
        .catch(err => err)
    }
  }
}
