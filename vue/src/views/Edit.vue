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
                autocomplete="off"
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
                autocomplete="off"
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
              />
            </td>
          </tr>
        </table>
        <button
          class="button"
          @click="removetext(age,score,comment)"
        >
          リセット
        </button>
        <button
          class="button"
          :disabled="!inputCheck || ageCheck || scoreCheck"
          :class="{'disabled': ageCheck || scoreCheck || !inputCheck}"
          @click="add"
        >
          {{ changeButtonText }}
        </button>
        <div>＊スコアは<br>-100から100の範囲で<br>指定してください。</div>
        <div v-if="ageCheck">
          年齢が不正です
        </div>
        <div v-if="scoreCheck">
          スコアが不正です
        </div>
      </div>
      <div class="listInfo">
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
              v-for="(content,index) in contents"
              :key="index"
            >
              <!-- わからん -->
              <td>
                {{ content.age }}
              </td>
              <td>
                {{ content.score }}
              </td>
              <td class="commentTable">
                {{ content.comment }}
              </td>
              <button
                class="button"
                @click="edit(index)"
              >
                修正
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
    <div class="editGraph">
      <div
        v-if="loaded"
        id="chart"
      >
        <Chart />
      </div>
    </div><br><br>
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
      age: null,
      score: null,
      comment: '',
      isActive: false,
      contents: [], // ここに情報あると空のデータが存在してしまう
      load: true,
      editIndex: -1 // 初期値-1だと
    }
  },
  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    },
    changeButtonText () {
      return this.editIndex === -1 ? '追加' : '完了'
    },
    inputCheck () {
      return this.age && this.score
    },
    ageCheck () {
      return this.age < 0 || this.age > 100
    },
    scoreCheck () {
      return this.score < -100 || this.score > 100
    }
  },
  mounted () {
    this.setContents()
  },
  methods: {
    setContents () {
      this.contents = this.$store.state.chart.contents.map((content) => {
        return {
          age: content.age,
          score: content.score,
          comment: content.comment
        }
      })
    },
    // テキストボックス値削除
    removetext: function (removeitem) {
      this.age = ''
      this.score = ''
      this.comment = ''
    },
    add () {
      this.isActive = true
      if (this.editIndex === -1) { // ifの時addボタンの挙動
        this.contents.push(
          {
            age: this.age,
            score: this.score,
            comment: this.comment
          })
      } else { // elseの時editボタンの挙動
        this.contents.splice(
          this.editIndex,
          1,
          {
            age: this.age,
            score: this.score,
            comment: this.comment
          }
        )// splice配列の最初？配列から要素を削除・追加して組み替え。１は第２引数で削除する数
        this.editIndex = -1
      }
      this.age = ''
      this.score = ''
      this.comment = ''
      console.log(this.contents)
    },
    edit (index) {
      this.editIndex = index
      this.age = this.contents[index].age // indexに応じたageが出てくる
      this.score = this.contents[index].score
      this.comment = this.contents[index].comment
      this.$refs.editor.focus() // フォーカスを設定
    },
    deleteContents (index) {
      this.contents.splice(index, 1)
    },
    update () {
      this.$store.dispatch(
        'chart/addData',
        this.contents
      )
      const currentParentId = this.$store.state.chart.contents[0].parentId
      const currentUserId = this.$store.state.auth.userId
      const apiContents = {
        parentId: currentParentId,
        userId: currentUserId,
        children: this.$store.state.chart.contents
      }
      this.$store.dispatch('chart/editContent', apiContents)
      console.log(apiContents)
      this.$store.dispatch('chart/addContent', apiContents)
    }
  }
}
</script>

<style>
#editSection {
  background: #F3F3F9;
  text-align: center;
}
#input {
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
.editTitle,h1 {
  color: #565452;
  font-size: 30px;
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

.button.disabled {
  background: #ffbab3;
}

.commentTable {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  }
.listInfo {
  overflow: hidden;
  background:#FFF;
  border-radius: 20px;
  color: #565452;
  width: 60%;
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
