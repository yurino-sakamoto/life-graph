<template>
  <div id="editSection">
    <div>
      <Header />
    </div>
    <div class="editTitle">
      編集
    </div>
    <div id="editForm">
      <h1>Life Graph</h1>
      <div id="input">
        <table id="field">
          <tr>
            <th scope="row">
              <label for="age">
                年齢
              </label>
            </th>
            <td>
              <input
                id="age"
                ref="editor"
                v-model="age"
                type="number"
                @keyup.enter="changeContents"
              >
            </td>
          </tr>
          <tr>
            <th scope="row">
              <label for="score">
                満足度
              </label>
            </th>
            <td>
              <input
                id="score"
                ref="editor"
                v-model="score"
                type="number"
                @keyup.enter="changeContents"
              >
            </td>
          </tr>
          <tr>
            <th scope="row">
              <label for="comment">
                コメント
              </label>
            </th>
            <td>
              <textarea
                id="comment"
                ref="editor"
                v-model="comment"
                cols="30"
                rows="5"
                placeholder="内容を入力してください。"
                @keyup.enter="changeContents"
              />
            </td>
          </tr>
        </table>
        <div id="action">
          <button
            id="reset"
            @click="reset"
          >
            クリア
          </button>
          <button
            id="submit"
            @click="add"
          >
            {{ changeButtonText }}
          </button>
        </div>
      </div>
      <div id="list">
        <table>
          <thead>
            <tr>
              <th>年齢</th>
              <th>スコア</th>
              <th>コメント</th>
            </tr>
          </thead>
          <tbody v-if="isActive">
            <tr
              v-for="content in contents"
              :key="content.age"
            >
              <td>{{ content.age }}</td>
              <td>{{ content.score }}</td>
              <td>{{ content.comment }}</td>
              <button
                class="button"
                @click="edit(index)"
              >
                編集
              </button>
              <button
                class="button"
                @click="deleteContents(index)"
              >
                削除
              </button>
            </tr>
          </tbody>
        </table>
      </div>
      <button
        class="button"
        @click="update()"
      >
        更新
      </button>
    </div>
    <div class="chartArea">
      <div
        v-if="loaded"
        id="chart"
      >
        <Chart />
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../components/Header.vue'
import Chart from '../components/Chart.vue'
export default {
  name: 'Edit',
  components: {
    Header,
    Chart
  },
  data () {
    return {
      isActive: false,
      contents: [
        {
          age: '',
          score: '',
          comment: ''
        }
      ],
      load: true,
      editIndex: -1
    }
  },
  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    },
    changeButtonText () {
      return this.editIndex === -1 ? '追加' : '編集'
    }
  },
  methods: {
    reset () {
      this.age = ''
      this.score = ''
      this.comment = ''
      // console.clear(this.age)
      // console.clear(this.score)
      // console.clear(this.comment)
    },
    add () {
      this.isActive = true
      if (this.editIndex === -1) { // ifの時addボタンの挙動
        this.contents.push({ age: this.age, score: this.score, comment: this.comment }) // 配列の最後に
        this.age = ''
        this.score = ''
        this.comment = ''
      } else { // elseの時editボタンの挙動
        this.contents.splice(this.editIndex, 1, { age: this.age, score: this.score, comment: this.comment }) // splice配列の最初？ 配列から要素を削除・追加して組み替え
        this.age = ''
        this.score = ''
        this.comment = ''
        this.editIndex = -1
      }
      // console.log(this.age)
      // console.log(this.score)
      // console.log(this.comment)

      const content = {
        age: this.age,
        score: this.score,
        comment: this.comment
      }
      this.$store.dispatch('addContent', content)
    },
    submit () {
      this.isActive = true
      if (this.age === '') return
      const content = {
        age: this.age,
        score: this.score,
        comment: this.comment
      }
      this.content.push(content)
      this.$store.dispatch('addContent', content)
      this.age = ''
      this.score = ''
      this.comment = ''
    },
    deleteContents (index) {
      this.contents.splice(index, 1)
    },
    edit (index) {
      this.editIndex = index
      this.score = this.contents[index]
      this.comment = this.contents[index]
      this.$refs.editor.focus() // フォーカスを設定
    }
  }
}
</script>

<style>
.editSection {
  background: #F3F3F9;
  width: 100%;
  height: 1000px;
  margin: 0 auto;
  text-align: center;
  padding: 30px;
  display: inline-block;
}
.editTitle,h1 {
  color: #565452;
  font-size: 30px;
}
#reset {
  border: none;
  outline: none;
  background:#FE5F52;
  border-radius: 10px;
  margin: 10px auto;
  padding: 6px;
  font-size: 12pt;
  color:#FFF
}
#submit {
  border: none;
  outline: none;
  background:#FE5F52;
  border-radius: 10px;
  margin: 10px auto;
  padding: 6px;
  font-size: 12pt;
  color:#FFF
}
.button {
  border: none;
  outline: none;
  background:#FE5F52;
  border-radius: 10px;
  margin: 10px auto;
  padding: 6px;
  font-size: 12pt;
  color:#FFF
}
.inputInfo {
  background:#FFF;
  border-radius: 20px;
  color: #565452;
  width: 40%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 10px auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
.editGraph {
  background:#FFF;
  color: #565452;
  border-radius: 20px;
  width: 50%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
</style>
