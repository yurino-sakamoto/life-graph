import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    parentId: null,
    contents: [],
    otherMemberContents: [],
    load: false,
    loaded: false,
    error: ''
  },
  mutations: {
    addContentMutation (state, payload) {
      state.contents = []
      if (payload.length > 0) {
        state.parentId = payload[0].parent_id
        payload.map((content) => {
          const data = {
            id: content.id,
            age: content.age,
            score: content.score,
            comment: content.comment
          }
          state.contents.push(data)
        })
      }
      state.loaded = !state.loaded
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
    getReferenceMutation (state, contents) {
      state.otherMemberContents = contents
      state.loaded = true
    },
    loadFalse (state) {
      state.loaded = false
    }
  },
  actions: {
    async addContent ({ commit, rootState }, userId) {
      const url = '/api/life-graphs/' + userId
      await axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('addContentMutation', res.data))
        .catch(err => err)
    },
    addData ({ commit }, contents) {
      commit('addDataMutation', contents)
    },
    async editContent ({ dispatch, commit, rootState }, apiContents) {
      const url = 'api/life-graphs'
      await axios.post(url, apiContents, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      })
        .catch(err => commit('error', err))
      dispatch('addContent', apiContents.userId)
    },
    async getReference ({ commit, rootState }, referenceId) {
      const url = '/api/life-graphs/' + referenceId
      await axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('getReferenceMutation', res.data))
        .catch(err => err)
    }
  }
}
