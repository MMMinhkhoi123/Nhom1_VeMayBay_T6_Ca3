<template>
  <div class="list_airfield">
    <table id="customers">
      <tr>
        <th>Tên địa điểm bay</th>
        <th>Quốc gia</th>
        <th>Địa chỉ</th>
        <th>Tùy chỉnh</th>
      </tr>
      <tr v-for="item in data.list" :key="item.id">
        <td @click.self="update(item)">{{ item.name }}</td>
        <td @click.self="update(item)">{{ item.contry }}</td>
        <td @click.self="update(item)">{{ item.adrress }}</td>
        <td class="setting">
          <button class="delete" @click="deletes(item.id)">delete</button>
        </td>
      </tr>
    </table>

    <!-- // phan trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_airfield.page.page - 1)"
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
        @click="chagepage($store.state.md_airfield.page.page + 1)"
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
      <p>Bạn có Chắc xóa airfield này không ?</p>
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
  <!-- mess -->
  <basemes
    :class="$store.state.md_airfield.mess_airfield == null ? null : 'show_mess'"
    :style="
      $store.state.md_airfield.status_airfield == 0
        ? { background: 'rgb(118, 226, 118)' }
        : $store.state.md_airfield.status_airfield == 1
        ? { background: 'yellow' }
        : { background: 'red' }
    "
  >
    <template #content>
      <p>{{ $store.state.md_airfield.mess_airfield }}</p>
    </template>
  </basemes>
</template>
<script>
import store from "@/store";
import { computed, reactive } from "vue";
import mess from "@/components/Admin/NOTI/MessVue.vue";
import notification from "../NOTI/DeleteVue.vue";
export default {
  components: {
    basemes: mess,
    basenotification: notification,
  },
  setup() {
    store.state.md_airfield.mess_airfield = null;
    store.dispatch("md_airfield/set_airfield");
    const data = computed(() => {
      return store.state.md_airfield.airfield;
    });
    const deletes = (id) => {
      data_remove.status = true;
      data_remove.id = id;
    };
    const data_remove = reactive({
      id: null,
      status: false,
    });
    const submit_delete = () => {
      data_remove.status = false;
      store.state.md_airfield.mess_airfield = null;
      store.dispatch("md_airfield/delete_airfield", data_remove.id);
      setTimeout(() => {
        if (store.state.md_airfield.mess_airfield == null) {
          store.state.md_airfield.mess_airfield = "airfield delete success !";
        }
      }, 200);
    };
    const chagepage = (item) => {
      store.state.md_airfield.page.page = item;
      store.dispatch("md_airfield/set_airfield");
    };
    const update = (value) => {
      store.state.md_airfield.data.id = value.id;
      store.state.md_airfield.data.name = value.name;
      store.state.md_airfield.data.adrress = value.adrress;
      store.state.md_airfield.data.contry = value.contry;
    };
    return {
      data,
      deletes,
      data_remove,
      submit_delete,
      chagepage,
      update,
    };
  },
};
</script>
<style scoped>
/* table  */
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
#customers td,
#customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even) {
  background-color: #f2f2f2;
}

#customers tr:hover {
  background-color: #ddd;
}

#customers th {
  padding-top: 12px;
  padding-bottom: 12rgb (12, 175, 115);
  text-align: left;
  background-color: #3024de;
  color: white;
}
/* end table */
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

/* ///end mess */

/* notification */
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

h4 {
  margin-left: 10px;
}
.list_airfield::-webkit-scrollbar {
  display: none;
}
.list_airfield {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 10px;
  justify-content: center;
  position: relative;
  padding-bottom: 60px;
}
.setting {
  text-align: center;
}
.setting > button {
  padding: 10px 20px;
  border: none;
  margin: 0 5px;
  color: rgb(255, 255, 255);
  font-weight: 700;
  border-radius: 4px;
}
.delete {
  background: rgba(255, 28, 28, 0.779);
}
</style>
