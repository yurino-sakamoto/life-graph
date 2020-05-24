import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    parentId: null,
    contents: [],
    otherMemberContents: [], // 検索結果のグラフの要素が入る
    load: false,
    loaded: false,
    error: ''
  },
  mutations: {
    addContentMutation (state, payload) {
      state.contents = []
      if (payload.length > 0) { // parentIdを配列の外に出し、登録APIと合致
        state.parentId = payload[0].parent_id
        payload.map((content) => {
          const data = {
            age: content.age,
            score: content.score,
            comment: content.comment
          }
          state.contents.push(data)
        })
      }
      state.loaded = !state.loaded // アクションが走った時にTorF切替。変化したことを監視しているので、TかFかはなんでもいい。
    },
    addDataMutation (state, contents) {
      state.contents = contents
    },
    error (state, err) {
      state.error = err
    },
    clearState (state) {
      state.parentId = null
      state.contents = []
      state.otherMemberContents = []
    },
    clearError (state) {
      state.error = ''
    },
    getReferenceMutation (state, contents) { // 検索画面のやつ
      state.otherMemberContents = contents
      state.loaded = true
    },
    loadFalse (state) {
      state.loaded = false
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
      const url = 'api/life-graphs'
      await axios.post(url, apiContents)
        .catch(err => commit('error', err))
      dispatch('addContent', apiContents.userId)
    },
    async getReference ({ commit }, referenceId) {
      const url = '/api/reference/' + referenceId
      await axios.get(url).then(res => commit('getReferenceMutation', res.data))
        .catch(err => err)
    }
  }
}
