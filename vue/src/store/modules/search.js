// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)

// export default new Vuex.Store({
//   state: {
//     username: "",
//     user_id: "",
//     updated_id: "",
//     created_id: ""
//   },
//   mutations: {
//     getSearchGraphs (state, username, user_id, updated_at, created_at) {
//       state.username = username
//       state.user_id = user_id
//       state.updated_at = updated_at
//       state.created_at = created_at
//     }
//   },
//   actions: {
//     async SearchGraphInfos ({ commit }) {
//       // const url = '/api/search'
//       const payload = {
//         username: "",
//         user
//       }
//       try {
//         const res = await Promise.all([
//           axios.get('/likename'),
//           axios.get('/startDate'),
//           axios.get('/updateDate')
//         ])
//         return commit('setSearchGraphs', res.data)
//       } catch (err) {
//         return err
//       }
//     }
//   }
// })

// methods: {
//   getAddress() {
//     this.$store.commit("setSearchGraphs", )
//     this.$store.dispatch("SearchGraphInfos");
//   }
// }
