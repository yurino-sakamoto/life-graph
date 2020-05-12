<template>
  <div class="headderSection">
    <div class="logoParent">
      <img class="pageLogo" src="../assets/logo.png" alt="ロゴ">
    </div>
    <div class="userName_authorityLevel">
      <div class="userName">
        {{username}}
      </div>
      <div class="authorityLevel">
        <p v-if="user==general">一般ユーザー</p>
        <p v-else-if="user==admin">管理者</p>
        <p v-else>オーナー</p>
      </div>
    </div>
  </div>
</template>

<script>
// export default {
//   data:{
//     user: general
//  },
//     return username
// }
</script>

<style scoped>

.headderSection{
  height: 70px;
  background-color: lightgrey;
}

.logoParent{
  text-align: left;
}

.pageLogo{
  width: 50px;
  margin: 13px 0px 0px 10px;
}

.userName_authorityLevel {
  position: relative;
  bottom: 64px;
  padding: 0pt 15pt 0pt 0pt;
  text-align: right;
}

.userName{
  font-weight: bold;
  font-size: 1.5rem;
  margin: 0pt 0pt 0pt 0pt;
}

.authorityLevel{
  margin: 0pt 0pt 0pt 0pt;
}

</style>

<!--
取得した名前を表示
if文
-->
