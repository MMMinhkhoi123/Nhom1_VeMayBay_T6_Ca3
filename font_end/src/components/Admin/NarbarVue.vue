<template>
  <div v-if="this.$store.state.post.authentication != null" class="navbar">
    <div class="content">
      <strong>Admin-{{ this.$store.state.post.authentication.role[0] }}</strong>
      welcome
    </div>
    <div class="acount">
      <h4>{{ this.$store.state.post.authentication.fullname }}</h4>
      <span @click="this.$router.push({ name: 'profiless' })">
        <font-awesome-icon class="icon" :icon="['fas', 'user-pen']" />
      </span>
    </div>
  </div>
</template>
<script>
export default {
  setup() {},
};
</script>
<style scoped>
.navbar {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  background: white;
  display: flex;
  justify-content: space-between;
}
span {
  width: 40px;
  height: 40px;
  background: rgb(56, 55, 55);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.icon {
  font-size: 15px;
  color: white;
}
.acount,
.content {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 0 30px;
  padding: 10px;
  border-radius: 0 0 30px 20px;
}
.content {
  font-size: 20px;
}
</style>
