<template>
  <div class="list_airplane">
    <div
      v-for="(item, index) in arraylist.list"
      :key="index"
      class="item_airplane"
    >
      <div class="info_airplane">
        <h3>Máy Bay {{ item.name }}</h3>
        <p>Hãng {{ item.name_trademark }}</p>
        <p>Số ghế {{ item.size }}</p>
        <p>Trống {{ item.empty }}</p>
        <p>Đầy {{ item.full }}</p>
        <div class="setting">
          <button @click="update(item.id)" class="edit">
            <font-awesome-icon :icon="['fas', 'pen-to-square']" /> Edit
          </button>
          <button @click="deletes(item.id)" class="delete">
            <font-awesome-icon :icon="['fas', 'trash']" /> Delete
          </button>
        </div>
      </div>
    </div>
    <!-- phân trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_airplane.page.page - 1)"
        v-if="arraylist.page > 1"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </div>
      <div v-for="item in arraylist.toltalpage" :key="item">
        <div
          @click="chagepage(item)"
          :style="
            item == arraylist.page
              ? { background: 'blue', color: 'white' }
              : null
          "
          class="item_page"
        >
          {{ item }}
        </div>
      </div>
      <div
        @click="chagepage($store.state.md_airplane.page.page + 1)"
        v-if="arraylist.page < arraylist.toltalpage"
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
      <p>Bạn có Chắc xóa Máy bay này không ?</p>
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
    :class="$store.state.md_airplane.mess_airplane == null ? null : 'show_mess'"
    :style="
      $store.state.md_airplane.mess_airplane == 'Airplane Add success !'
        ? { background: 'rgb(118, 226, 118)' }
        : $store.state.md_airplane.mess_airplane == ' Airplane Update success !'
        ? { background: 'yellow' }
        : { background: 'red' }
    "
  >
    <template #content>
      <p>{{ $store.state.md_airplane.mess_airplane }}</p>
    </template>
  </basemes>
</template>
<script>
import mess from "@/components/Admin/NOTI/MessVue.vue";
import notification from "../NOTI/DeleteVue.vue";
import { computed, reactive } from "vue";
import store from "@/store";
export default {
  components: {
    basenotification: notification,
    basemes: mess,
  },
  setup() {
    store.state.md_airplane.mess_airplane = null;
    store.dispatch("md_airplane/get_airplane");
    const arraylist = computed(() => {
      return store.state.md_airplane.airplane;
    });
    const deletes = (id) => {
      data_remove.status = true;
      data_remove.id = id;
    };

    const submit_delete = () => {
      data_remove.status = false;
      store.dispatch("md_airplane/delete_airplane", data_remove.id);
      store.state.md_airplane.mess_airplane = null;
      setTimeout(() => {
        if (store.state.md_airplane.mess_airplane == null) {
          store.state.md_airplane.mess_airplane = "Airplane Delete success !";
        }
      }, 100);
    };

    const data_remove = reactive({
      id: null,
      status: false,
    });
    const update = (id) => {
      store.state.md_airplane.update_airplane.statuschonse = 1;
      const data = store.getters["md_airplane/getairplaneid"](id)[0];
      store.state.md_airplane.update_airplane.id = id;
      store.state.md_airplane.update_airplane.name = data.name;
      store.state.md_airplane.update_airplane.id_trademark = data.id_trademark;
      store.state.md_airplane.update_airplane.array_type = data.typechair;
    };

    const chagepage = (item) => {
      store.state.md_airplane.page.page = item;
      store.dispatch("md_airplane/get_airplane");
    };
    return {
      arraylist,
      deletes,
      update,
      data_remove,
      submit_delete,
      chagepage,
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
/* end mess */
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
/* end notification */
.edit {
  background: blue;
}
.delete {
  background: red;
}
.list_airplane {
  margin-top: 10px;
  background: rgb(255, 255, 255);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  padding-bottom: 60px;
  position: relative;
  padding-top: 10px;
}
.info_airplane {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  gap: 10px;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.422);
  justify-content: space-around;
}
.item_airplane {
  background: white;
  border-radius: 10px;
  margin: 5px 20px;
}
.setting {
  display: flex;
  justify-content: center;
  gap: 10px;
  padding: 10px;
}
button {
  padding: 10px 20px;
  color: white;
  border: none;
  border-radius: 5px;
  font-weight: 600;
}
</style>
