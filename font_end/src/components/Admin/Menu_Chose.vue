<template>
  <div v-if="this.$store.state.post.authentication != null" class="menu">
    <div class="admin_logo">
      <h1>DASH UI</h1>
      <p>admin {{ this.$store.state.post.authentication.role[0] }}</p>
    </div>

    <div
      v-if="$store.state.post.authentication.role.includes('creater')"
      class="location"
      @click="this.$router.push({ name: 'location' })"
    >
      <font-awesome-icon :icon="['fas', 'location-dot']" />
      <strong>Location</strong>
    </div>

    <div
      v-if="$store.state.post.authentication.role.includes('creater')"
      class="airfield"
      @click="this.$router.push({ name: 'airfield' })"
    >
      <font-awesome-icon :icon="['fas', 'location-crosshairs']" />
      <strong>Airfield</strong>
    </div>

    <div
      v-if="$store.state.post.authentication.role.includes('creater')"
      class="trademark"
      @click="this.$router.push({ name: 'trademark' })"
    >
      <font-awesome-icon :icon="['fas', 'trademark']" />
      <strong>Trademark</strong>
    </div>
    <div
      v-if="$store.state.post.authentication.role.includes('creater')"
      class="Ticker"
      @click="this.$router.push({ name: 'typechair' })"
    >
      <font-awesome-icon :icon="['fas', 'chart-bar']" />
      <strong> Typechair</strong>
    </div>
    <div
      v-if="$store.state.post.authentication.role.includes('creater')"
      class="airplane"
      @click="this.$router.push({ name: 'airplane' })"
    >
      <font-awesome-icon :icon="['fas', 'plane']" />
      <strong>Airplane</strong>
    </div>
    <div @click="this.$router.push({ name: 'ticker' })" class="Ticker">
      <font-awesome-icon :icon="['fas', 'ticket']" />
      <strong>Ticker</strong>
    </div>
    <div
      v-if="$store.state.post.authentication.role.includes('creater')"
      @click="this.$router.push({ name: 'flight' })"
      class="flight"
    >
      <font-awesome-icon :icon="['fas', 'plane-circle-exclamation']" />
      <strong>Flight</strong>
    </div>
    <div
      v-if="$store.state.post.authentication.role.includes('checker')"
      @click="this.$router.push({ name: 'customer' })"
      class="customer"
    >
      <font-awesome-icon :icon="['fas', 'users-between-lines']" />
      <strong>Customer</strong>
    </div>
    <div class="info">
      <div>
        <span>@2023-2024</span
        ><strong style="color: red"
          >Admin-{{ this.$store.state.post.authentication.role[0] }}</strong
        >
      </div>
      <strong>version 1.0.0</strong>
    </div>
  </div>
</template>
<script>
export default {
  setup() {
    return {};
  },
};
</script>
<style scoped>
.info {
  border-top: 1px solid rgb(219, 218, 218);
}
.menu {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  background: rgb(255, 255, 255);
  display: flex;
  min-width: 250px;
  flex-direction: column;
}
.menu > div {
  padding: 15px;
  font-weight: 500;
  cursor: pointer;
}
.menu > div:hover:not(.admin_logo) {
  color: blue;
}
.menu > div:hover:not(.admin_logo) {
  background: rgb(216, 216, 216);
}
.menu > h1 {
  padding: 20px;
}
strong {
  margin-left: 10px;
}
.admin_logo {
  background: rgb(24, 151, 255);
  color: white;
}
</style>
