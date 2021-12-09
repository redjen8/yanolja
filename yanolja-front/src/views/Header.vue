<template>
  <div id="navbar">
    <b-navbar toggleable="lg" class="nav" type="dark" variant="primary" sticky>
      <b-navbar-brand class="navbar-brand" to="/">Yanolja</b-navbar-brand>

      <b-navbar-toggle target="nav-main-collapse"></b-navbar-toggle>

      <b-collapse id="nav-main-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/about">About</b-nav-item>
          <b-nav-item to="/room">Room</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ms-auto">

          <b-nav-item-dropdown text="Lang">
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              User
            </template>
            <b-dropdown-item to="/login">Login</b-dropdown-item>
            <b-dropdown-item to="/member">Sign In</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>
#navbar {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*text-align: center;*/
  margin-bottom: 20px;
  color: #2c3e50;
}

.navbar-brand {
  margin-left: 15px;
  font-family: "Arial Black",serif;
}

</style>