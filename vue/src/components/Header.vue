<template>
  <div class="headderSection">
    <Header />
    <div class="logoParent">
      <img class="pageLogo" src="../assets/logo.png" alt="ロゴ">
    </div>
    <div class="userName_authorityLevel">
      <div class="userName">
        小澤こうた
      </div>
      <div class="authorityLevel">
        一般ユーザー
      </div>
    </div>
  </div>
</template>

<style scoped>

.headderSection{
  height: 70px;
  background-color: lightgrey;
}
.logoParent{
  text-align: left;
}
.pageLogo{ /*これがロゴマークになる？？？*/
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

<!--ヘッダー-->
