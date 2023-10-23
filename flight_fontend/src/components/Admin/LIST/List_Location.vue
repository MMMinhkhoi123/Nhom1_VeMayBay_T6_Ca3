<template>
  <div class="list_location">
    <form
      @click.self="updates(item.id)"
      v-for="item in data.list"
      :key="item.id"
      class="item"
    >
      <img
        class="img"
        :src="
          'http://localhost:8081/img/location/' + item.id + '&3&' + item.img
        "
      />
      <div class="test_content">
        <div class="content">
          <span>{{ item.name }}</span>
          <span>{{ item.contry }}</span>
        </div>
        <font-awesome-icon
          @click="deletes(item.img, item.id)"
          class="icon"
          :icon="['fas', 'delete-left']"
        />
      </div>
    </form>

    <!-- phân trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_location.page.page - 1)"
        v-if="data.page > 1"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </div>
      <div v-for="item in data.toltalpage" :key="item">
        <div
          @click="chagepage(item)"
          :style="
            item == data.page ? { background: 'blue', color: 'white' } : null
          "
          class="item_page"
        >
          {{ item }}
        </div>
      </div>
      <div
        @click="chagepage($store.state.md_location.page.page + 1)"
        v-if="data.page < data.toltalpage"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-right']" />
      </div>
    </div>
  </div>
  <!-- Thông báo ở đây 
   -->
  <div
    @click="data_remove.status = false"
    :class="data_remove.status == false ? null : 'show'"
    class="bg_black"
  ></div>
  <basenotification :class="data_remove.status == false ? null : 'show_from'">
    <template #content>
      <p>Bạn có Chắc xóa Địa Điểm này không ?</p>
    </template>
    <template #button>
      <button @click="submit_delete" class="remove">
        <font-awesome-icon :icon="['fas', 'trash']" /> Xóa
      </button>
      <button @click="data_remove.status = false" class="close">
        <font-awesome-icon :icon="['fas', 'xmark']" />
        Close
      </button>
    </template>
  </basenotification>

  <!--  mess -->
  <basemes
    :class="$store.state.md_location.mess_location == null ? null : 'show_mess'"
    :style="
      $store.state.md_location.status_location == 0
        ? { background: 'rgb(118, 226, 118)' }
        : $store.state.md_location.status_location == 1
        ? { background: 'yellow' }
        : { background: 'red' }
    "
  >
    <template #content>
      <p>{{ $store.state.md_location.mess_location }}</p>
    </template>
  </basemes>
  <!--end  mess -->
</template>
<script>
import store from "@/store";
import mess from "@/components/Admin/NOTI/MessVue.vue";
import { computed, reactive } from "vue";

import notification from "../NOTI/DeleteVue.vue";
export default {
  components: {
    basenotification: notification,
    basemes: mess,
  },
  setup() {
    store.dispatch("md_location/get_location");
    const data = computed(() => {
      return store.state.md_location.Location;
    });

    const data_remove = reactive({
      id: null,
      img: null,
      status: false,
    });

    const deletes = (valuex, id) => {
      data_remove.status = true;
      data_remove.id = id;
      data_remove.img = valuex;
    };

    const submit_delete = () => {
      data_remove.status = false;
      store.state.md_location.mess_location = null;
      store.dispatch("md_location/delete_locations", data_remove.id);
    };

    const updates = (id) => {
      const data = store.getters["md_location/getlocationid"](id)[0];
      store.state.md_location.data.name = data.name;
      store.state.md_location.data.contry = data.contry;
      store.state.md_location.data.img = data.img;
      store.state.md_location.data.id = id;
      store.state.md_location.data.status = true;
    };

    const chagepage = (item) => {
      store.state.md_location.page.page = item;
      store.dispatch("md_location/get_location");
    };
    return {
      data,
      deletes,
      submit_delete,
      data_remove,
      chagepage,
      updates,
    };
  },
};
</script>
<style scoped>
/* pagnigation */

.group_page {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  position: absolute;
  right: 10px;
  bottom: 10px;
}
.item_page {
  display: flex;
  justify-content: center;
  align-items: center;
  background: white;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  border: 1px solid gray;
  cursor: pointer;
}
/* mess */
.show_mess {
  opacity: 1 !important;
  animation: showmess 6s cubic-bezier(0.19, 1, 0.22, 1) forwards;
}
@keyframes showmess {
  0% {
    bottom: 20px;
    left: -100%;
    opacity: 0 !important;
  }
  50% {
    bottom: 20px;
    left: 20px;
    opacity: 1 !important;
  }
  100% {
    bottom: 20px;
    left: -100%;
    opacity: 0 !important;
  }
}
/* Notofication  */
.remove,
.close {
  border: none;
  padding: 10px 20px;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}
.remove {
  background: red;
}
.close {
  background: orange;
}
.bg {
  position: fixed;
  inset: 0;
}
.bg_black {
  position: fixed;
  inset: 0;
  transition: 0.3s;
  background: rgba(0, 0, 0, 0.18);
  opacity: 0;
  pointer-events: none;
}
.show {
  opacity: 1 !important;
  pointer-events: visible !important;
}
.show_from {
  top: 50% !important;
  opacity: 1 !important;
}
.list_location::-webkit-scrollbar {
  display: none;
}
.list_location {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 10px 50px;
  position: relative;
  padding-bottom: 60px;
  justify-content: space-around;
}
.item {
  display: flex;
  grid-template-columns: 100px 1fr;
  background: rgb(255, 255, 255);
  min-width: 250px;
  position: relative;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.354);
}
.img {
  width: 100px;
  height: 100px;
  flex: 0;
  pointer-events: none;
}
.content {
  display: flex;
  flex-direction: column;
  padding: 20px;
  font-weight: 700;
  pointer-events: none;
}

.icon {
  position: absolute;
  top: -10px;
  right: -10px;
  font-size: 30px;
  transform: rotate(-20deg);
  color: red;
}
.test_content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
