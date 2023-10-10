<template>
  <div class="mainlist">
    <div class="meta">
      <h3 @click="this.$router.go(-1)" class="title">
        <font-awesome-icon :icon="['fas', 'chevron-left']" /> Quay về
      </h3>
    </div>
    <div v-if="$store.state.list.history.length > 0" class="list">
      <div v-for="item in array" :key="item.id" class="itemtick">
        <div class="top">
          <div class="trademark">
            <baseimg
              :style="{ width: '100%', height: '50px' }"
              :path="'trademark/' + item.imgtrademark"
            ></baseimg>
            <span>{{ item.nametrademark }}</span>
          </div>
          <div class="time">
            <span>{{ culdate(item.timego) }}</span>
            <span>{{ culdate(item.timefish) }}</span>
          </div>
        </div>
        <div class="bottom">
          <div class="price">
            <span>{{ item.price }} VND</span>
          </div>
          <div class="location">
            <span>Từ {{ item.locationgo }}</span>
            <span>Đến {{ item.locationfish }}</span>
          </div>
          <div class="timeby">
            <span>Time buy: </span>
            <span
              >{{ culdate(item.timemua) }}
              <font-awesome-icon class="icon" :icon="['fas', 'check']"
            /></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import img from "@/components/Admin/ImagesVue.vue";
import store from "@/store";
import { computed } from "vue";
export default {
  components: {
    baseimg: img,
  },
  setup() {
    const culdate = (value) => {
      const dates = new Date(value);
      const minu = dates.getMinutes();
      const hour = dates.getHours();
      return (
        hour +
        ":" +
        minu +
        "  " +
        dates.getDate() +
        "/" +
        (dates.getUTCMonth() + 1) +
        "/" +
        dates.getFullYear()
      );
    };
    const array = computed(() => {
      return store.state.list.history;
    });
    return { culdate, array };
  },
};
</script>
<style scoped>
.trademark {
  display: flex;
  justify-content: center;
  gap: 10px;
  align-items: center;
  font-weight: 800;
}
.list {
  margin: 30px 200px;
}
/* tick  */
.icon {
  color: rgb(89, 206, 89);
}
image {
  width: 50px;
  height: 50px;
}
.itemtick {
  background: rgb(250, 250, 252);
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.457);
  border-radius: 10px;
  margin: 10px 0;
}
.time,
.location {
  display: flex;
  gap: 10px;
}
.top,
.bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
}
/* end tick */
.meta {
  background: rgb(68, 91, 202);
}
.mainlist {
  border-radius: 10px;
  overflow: hidden;
  margin-top: 10px;
  min-height: 100vh;
  position: relative;
  z-index: 10;
  background: white;
}
.title {
  margin: 0;
  padding: 20px;
  cursor: pointer;
}
</style>
