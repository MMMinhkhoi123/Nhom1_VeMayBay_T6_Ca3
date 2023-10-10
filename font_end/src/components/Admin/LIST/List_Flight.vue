<template>
  <div class="list_flight">
    <div v-for="item in list.list" :key="item.id" class="item">
      <div class="left">
        <div class="top">
          <span v-if="$store.state.md_airplane.airplane.length > 0"
            >May Bay
            {{
              $store.getters["md_airplane/getairplaneid"](
                item.id_airplane
              )[0] == null
                ? null
                : $store.getters["md_airplane/getairplaneid"](
                    item.id_airplane
                  )[0].name
            }}</span
          >
          <span v-if="$store.state.md_airfield.airfield.length > 0"
            >Sân bay
            {{
              $store.getters["md_airfield/getairfieldid"](item.id_airfield)[0]
                .name
            }}</span
          >
          <span v-if="$store.state.md_location.Location.length > 0"
            >Từ
            {{
              $store.getters["md_location/getlocationid"](
                item.id_location_departure
              )[0].name
            }}</span
          >
          <span v-if="$store.state.md_location.Location.length > 0"
            >Đến
            {{
              $store.getters["md_location/getlocationid"](
                item.id_location_complete
              )[0].name
            }}</span
          >
        </div>
        <div class="bottom">
          <span>Bay Lúc {{ converttime(item.date_departure) }}</span>
          <span>Thời gian dự kiến {{ converttime(item.date_complete) }}</span>
          <span>Giá {{ item.price }}/ vé </span>
        </div>
      </div>
      <div class="end">
        <button @click="update(item.id)" class="edit">
          <font-awesome-icon :icon="['fas', 'pen-to-square']" /> edit
        </button>
        <button @click="deletes(item.id)" class="remove">
          <font-awesome-icon :icon="['fas', 'trash']" /> Delete
        </button>
      </div>
    </div>

    <!-- phân trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_flight.page.page - 1)"
        v-if="$store.state.md_flight.page.page > 1"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </div>
      <div v-for="item in list.toltalpage" :key="item">
        <div
          @click="chagepage(item)"
          :style="
            item == list.page ? { background: 'blue', color: 'white' } : null
          "
          class="item_page"
        >
          {{ item }}
        </div>
      </div>
      <div
        @click="chagepage($store.state.md_flight.page.page + 1)"
        v-if="list.page < list.toltalpage"
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
      <p>Bạn có Chắc xóa chuyến bai này không ?</p>
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
  <!-- // mess -->
  <basemes
    :class="$store.state.md_flight.mess_flight == null ? null : 'show_mess'"
    :style="
      $store.state.md_flight.mess_flight == 'Create flight success !'
        ? { background: 'rgb(118, 226, 118)' }
        : $store.state.md_flight.mess_flight == 'Update flight success !'
        ? { background: 'yellow' }
        : { background: 'red' }
    "
  >
    <template #content>
      <p>{{ $store.state.md_flight.mess_flight }}</p>
    </template>
  </basemes>
  <!-- end mess -->
</template>
<script>
import store from "@/store";
import mess from "@/components/Admin/NOTI/MessVue.vue";
import notification from "../NOTI/DeleteVue.vue";
import { computed, reactive } from "vue";
export default {
  components: {
    basemes: mess,
    basenotification: notification,
  },
  setup() {
    store.state.md_flight.mess_flight = null;
    store.dispatch("md_flight/get_flight");
    store.dispatch("md_flight/get_all_flight");
    const list = computed(() => {
      return store.state.md_flight.flight;
    });
    const converttime = (value) => {
      const date = new Date(value);
      return (
        date.getHours() +
        " Giờ " +
        date.getMinutes() +
        " Phút  Ngày " +
        date.getDate() +
        "  Tháng " +
        date.getMonth() +
        "  Năm " +
        date.getFullYear()
      );
    };
    const update = (value) => {
      store.state.md_flight.data_check.price =
        store.getters["md_flight/getfligthid"](value).price;
      store.state.md_flight.data_check.id_airplane =
        store.getters["md_flight/getfligthid"](value).id_airplane;
      store.state.md_flight.data_check.id_locationgo =
        store.getters["md_flight/getfligthid"](value).id_location_departure;
      store.state.md_flight.data_check.id_locationand =
        store.getters["md_flight/getfligthid"](value).id_location_complete;
      store.state.md_flight.data_check.id_airfield =
        store.getters["md_flight/getfligthid"](value).id_airfield;
      store.state.md_flight.data_check.id =
        store.getters["md_flight/getfligthid"](value).id;
      const datago = new Date(
        store.getters["md_flight/getfligthid"](value).date_departure
      );
      const datacomne = new Date(
        store.getters["md_flight/getfligthid"](value).date_complete
      );
      store.state.md_flight.data_check.timemove = convert_time(datacomne);
      store.state.md_flight.data_check.timego = convert_time(datago);
      store.state.md_flight.data_check.date_go = datago;
      store.state.md_flight.data_check.date_move = datacomne;
    };

    const convert_time = (value) => {
      return value.getHours() + ":" + value.getMinutes();
    };
    const data_remove = reactive({
      id: null,
      status: false,
    });

    const deletes = (id) => {
      data_remove.status = true;
      data_remove.id = id;
    };

    const submit_delete = () => {
      data_remove.status = false;
      store.state.md_flight.mess_flight = null;
      store.dispatch("md_flight/delete_flight", data_remove.id);
      setTimeout(() => {
        if (store.state.md_flight.mess_flight == null) {
          store.state.md_flight.mess_flight = "Delete  flight success !";
        }
      }, 300);
    };
    const chagepage = (item) => {
      store.state.md_flight.page.page = item;
      //get
      store.dispatch("md_flight/get_flight");
    };
    return {
      data_remove,
      list,
      converttime,
      update,
      deletes,
      submit_delete,
      chagepage,
    };
  },
};
</script>
<style scoped>
/* pageagtion */
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
/* notication */
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
/* end notication */
/* mess */

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
/* mess */
.edit {
  background: blue;
}
.remove {
  background: red;
}
.list_flight {
  background: white;
  padding: 10px;
  position: relative;
  padding-bottom: 60px;
}

.item {
  margin: 5px;
  display: flex;
  justify-content: space-between;
  background: rgb(255, 255, 255);
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.422);
}
.top {
  grid: a;
}
.bottom {
  grid: b;
}
.end {
  grid: c;
}

.top > span,
.end > span,
.bottom > span {
  padding: 10px;
}
.top,
.bottom {
  display: flex;
  justify-content: space-around;
}
.end {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  gap: 10px;
}
button {
  padding: 10px 20px;
  color: white;
  font-weight: 600;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}
</style>
