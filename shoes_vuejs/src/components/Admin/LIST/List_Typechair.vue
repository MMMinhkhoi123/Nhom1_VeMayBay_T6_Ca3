<template>
  <div class="list_typechair">
    <form
      @submit.prevent="deletes(item.id)"
      v-for="item in data"
      :key="item.id"
      class="item"
    >
      <h4>{{ item.name }}</h4>
      <div class="cople_content">
        <span>Giá trả thêm: {{ item.price }}</span>
        <span>Hành lý: {{ item.luggage }}</span>
      </div>
      <font-awesome-icon
        @click="deletes(item.id)"
        class="icon"
        :icon="['fas', 'delete-left']"
      />
    </form>
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
  <!-- // mess -->
  <basemes
    :class="$store.state.md_typechair.mess_chaier == null ? null : 'show_mess'"
    :style="
      $store.state.md_typechair.mess_chaier == 'Typechair Add success !'
        ? { background: 'rgb(118, 226, 118)' }
        : { background: 'red' }
    "
  >
    <template #content>
      <p>{{ $store.state.md_typechair.mess_chaier }}</p>
    </template>
  </basemes>
</template>
<script>
import mess from "@/components/Admin/NOTI/MessVue.vue";
import store from "@/store";
import { computed, reactive } from "vue";
import notification from "../NOTI/DeleteVue.vue";
export default {
  components: {
    basemes: mess,
    basenotification: notification,
  },
  setup() {
    store.state.md_typechair.mess_chaier = null;
    store.dispatch("md_typechair/get_typechair");
    const data = computed(() => {
      return store.state.md_typechair.typechair;
    });
    const deletes = (id) => {
      data_remove.status = true;
      data_remove.id = id;
    };

    const submit_delete = () => {
      data_remove.status = false;
      store.dispatch("md_typechair/delete_typechair", data_remove.id);
      store.state.md_typechair.mess_chaier = null;
      setTimeout(() => {
        if (store.state.md_typechair.mess_chaier == null) {
          store.state.md_typechair.mess_chaier = "Type chair delete success !";
        }
      }, 200);
    };

    const data_remove = reactive({
      id: null,
      status: false,
    });

    return {
      data,
      deletes,
      data_remove,
      submit_delete,
    };
  },
};
</script>
<style scoped>
/*
notification
*/
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

h4 {
  margin-left: 10px;
}
.list_typechair::-webkit-scrollbar {
  display: none;
}
.list_typechair {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 10px;
}
.item {
  display: grid;
  grid-template-columns: 1fr 1fr;
  background: rgb(255, 255, 255);
  min-width: 300px;
  padding: 10px;
  position: relative;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.354);
}
.cople_content {
  display: flex;
  flex-direction: column;
  justify-content: center;
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
