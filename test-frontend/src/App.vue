<template>
  <div id="app">
    <nav class="navbar navbar-dark bg-dark">
      <a class="navbar-brand" href="#">
        <img src="./assets/logoTrimix.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Test
      </a>
    </nav>   
    <router-view/>
  </div>
</template>

<style>
#app { 
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
