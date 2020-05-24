import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    contents: [],
    load: false,
    loaded: false,
    error: ''
  },
  mutations: {
    addContentMutation (state, payload) {
      state.contents = payload
    },
    addDataMutation (state, contents) {
      state.contents = contents
    },
    clear (state) {
      state.contents = []
    },
    error (state, err) {
      state.error = err
    },
    clearError (state) {
      state.error = ''
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
    async editContent ({ dispatch, commit }, apiContents) {
      // console.log(apiContents)
      const url = 'api/life-graphs'
      await axios.post(url, apiContents)
        .catch(err => commit('error', err))
      dispatch('addContent', apiContents.userId)
    }
  }
}
