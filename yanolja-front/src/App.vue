<template>
  <div id="app">
    <div id="nav">
      <b-container fluid>
        <b-row>
          <b-col sm="2" align="start">
            <router-link to="/">Home</router-link>
          </b-col>
          <b-col sm="3">
            <router-link to="/about">About</router-link>
          </b-col>
          <b-col sm="3">
            <router-link to="/about">About</router-link>
          </b-col>
          <b-col sm="2" align="end">
            <router-link to="/member">Member</router-link>
          </b-col>
          <b-col sm="2" align="end">
            <router-link to="/login">Login</router-link>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  background-color: #000000;
  color: #FFFFFF;
  margin-bottom: 20px;
}

#nav a {
  font-weight: bold;
  color: #FFFFFF;
  text-decoration: none;
}

/*#nav a.router-link-exact-active {*/
/*  color: #42b983;*/
/*}*/
</style>
