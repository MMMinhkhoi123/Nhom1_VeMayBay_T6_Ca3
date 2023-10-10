<template>
  <div class="list_trademark">
    <form
      @submit.prevent="deletes(item.img, item.id)"
      v-for="item in data.list"
      :key="item.id"
      class="item"
    >
      <baseimg
        class="img"
        v-if="item.img"
        :path="'trademark/' + item.img"
      ></baseimg>
      <div class="test_content">
        <h4>{{ item.name }}</h4>
      </div>
      <font-awesome-icon
        @click="deletes(item.img, item.id)"
        class="icon"
        :icon="['fas', 'delete-left']"
      />
    </form>
    <!-- phân trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_trademark.page.page - 1)"
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
        @click="chagepage($store.state.md_trademark.page.page + 1)"
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
  <!-- messs  -->
  <basemes
    :class="
      $store.state.md_trademark.mess_trademark == null ? null : 'show_mess'
    "
    :style="
      $store.state.md_trademark.mess_trademark == 'trademark Add success !'
        ? { background: 'rgb(118, 226, 118)' }
        : { background: 'red' }
    "
  >
    <template #content>
      <p>{{ $store.state.md_trademark.mess_trademark }}</p>
    </template>
  </basemes>
</template>
<script>
import store from "@/store";
import { computed, reactive } from "vue";
import { getStorage, ref, deleteObject } from "firebase/storage";
import img from "../ImagesVue.vue";
import mess from "@/components/Admin/NOTI/MessVue.vue";
import notification from "../NOTI/DeleteVue.vue";
export default {
  components: {
    baseimg: img,
    basemes: mess,
    basenotification: notification,
  },
  setup() {
    store.state.md_trademark.mess_trademark = null;
    store.dispatch("md_trademark/set_trademarks");
    const data = computed(() => {
      return store.state.md_trademark.trademark;
    });

    const deletes = (value, id) => {
      data_remove.status = true;
      data_remove.id = id;
      data_remove.img = value;
    };

    const submit_delete = () => {
      data_remove.status = false;
      store.state.md_trademark.mess_trademark = null;
      store.dispatch("md_trademark/delete_trademarks", data_remove.id);
      setTimeout(() => {
        if (store.state.md_trademark.mess_trademark == null) {
          const storage = getStorage();
          const desertRef = ref(storage, "trademark/" + data_remove.img);
          deleteObject(desertRef);
          store.state.md_trademark.mess_trademark =
            "trademark delete success !";
        }
      }, 200);
    };

    const data_remove = reactive({
      id: null,
      img: null,
      status: false,
    });

    const chagepage = (item) => {
      store.state.md_trademark.page.page = item;
      store.dispatch("md_trademark/set_trademarks");
    };

    return {
      data,
      deletes,
      data_remove,
      submit_delete,
      chagepage,
    };
  },
};
</script>
<style scoped>
.img {
  width: 100px;
  height: 100px;
  flex: 0;
}
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
/* end mess */
/* 
notificaton */
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
/* end notification */
.list_trademark::-webkit-scrollbar {
  display: none;
}
.list_trademark {
  display: flex;
  flex-wrap: wrap;
  position: relative;
  justify-content: space-around;
  gap: 20px;
  padding: 10px;
  padding-bottom: 60px;
}
.item {
  display: flex;
  grid-template-columns: 100px 1fr;
  background: rgb(255, 255, 255);
  min-width: 270px;
  position: relative;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.354);
}

.test_content {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  margin-left: auto;
}
.icon {
  position: absolute;
  top: -10px;
  right: -10px;
  font-size: 30px;
  transform: rotate(-20deg);
  color: red;
}
</style>
