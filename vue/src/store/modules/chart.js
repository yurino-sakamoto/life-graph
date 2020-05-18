import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default {
  namespaced: true,

  state: {
    // 状態
    count: 0
  },
  getters: {
    // storeの状態を変更するメソッド(同期処理のみ)

  },
  mutation: {

  },
  actions: {
    // storeの状態を変更するメソッド(非同期処理も)

  }
}
