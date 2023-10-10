<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div class="top">
      <div
        v-if="!$store.state.md_airplane.update_airplane.statuschonse"
        class="ADD"
      >
        <button type="submit" class="create">
          <font-awesome-icon class="icon_add" :icon="['fas', 'square-plus']" />
          New AirPlane
        </button>
      </div>
      <div
        v-if="$store.state.md_airplane.update_airplane.statuschonse"
        class="UPDATE"
      >
        <button class="update">
          <font-awesome-icon class="icon_add" :icon="['fas', 'square-plus']" />
          Update
        </button>
      </div>
      <div
        v-if="$store.state.md_airplane.update_airplane.statuschonse"
        class="RELOAD"
      >
        <button type="button" @click="reset" class="reload">
          <font-awesome-icon :icon="['fas', 'repeat']" />
        </button>
      </div>

      <div class="name">
        <label for="">Ký Hiệu Máy bay</label>
        <div class="check">
          <input
            placeholder="Nhập..."
            :style="erro.name == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_airplane.update_airplane.name"
            type="text"
            name=""
            id=""
          />
          <span v-if="erro.name" class="erro">{{ erro.name }}</span>
        </div>
      </div>

      <div class="trademark">
        <label for="">Hãng Máy bay</label>
        <div class="check">
          <select
            :style="erro.trademark == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_airplane.update_airplane.id_trademark"
            name=""
            id=""
          >
            <option
              v-for="item in list_trademark"
              :key="item.id"
              :value="item.id"
            >
              {{ item.name }}
            </option>
          </select>
          <span v-if="erro.trademark" class="erro">{{ erro.trademark }}</span>
        </div>
      </div>

      <div class="typechair">
        <label>Chọn loại ghế</label>
        <div class="check">
          <select
            :style="erro.chair == null ? null : { border: '1px solid red' }"
            v-model="chonse_type.id_typechair"
            name="typechair"
          >
            <option
              v-for="item in list_typechair"
              :key="item.id"
              :value="item.id"
            >
              {{ item.name }}
            </option>
          </select>
          <span v-if="erro.chair" class="erro">{{ erro.chair }}</span>
        </div>
      </div>
      <div class="quality">
        <label>Số Lượng </label>
        <div class="check">
          <input
            placeholder="Nhập..."
            :style="erro.number == null ? null : { border: '1px solid red' }"
            v-model="chonse_type.quality"
            type="number"
            name=""
            id=""
          />
          <span v-if="erro.number" class="erro">{{ erro.number }}</span>
        </div>
      </div>
      <div class="addchair">
        <button type="button" class="push" @click="adddata">
          <font-awesome-icon :icon="['fas', 'plus']" /> Add Chair
        </button>
      </div>
    </div>
    <div class="bottom">
      <div
        v-for="(itemx, index) in $store.state.md_airplane.update_airplane
          .array_type"
        :key="index"
        class="item"
      >
        <h5>
          {{
            $store.getters["md_typechair/getonetypechair"](itemx.idtype)[0].name
          }}
        </h5>
        <p>{{ itemx.size }} ghế</p>
        <font-awesome-icon
          @click="
            $store.state.md_airplane.update_airplane.array_type.splice(index, 1)
          "
          class="icon"
          :icon="['fas', 'xmark']"
        />
      </div>
    </div>
  </form>
  <!-- Thông báo ở đây 
   -->
  <div
    @click="errom.status = false"
    :class="errom.status == false ? null : 'show'"
    class="bg_black"
  ></div>
  <basenotification :class="errom.status == false ? null : 'show_from'">
    <template #content>
      <p>{{ errom.mess }} ?</p>
    </template>
    <template #button>
      <button @click="errom.status = false" class="close">
        <font-awesome-icon :icon="['fas', 'xmark']" />
        Close
      </button>
    </template>
  </basenotification>
</template>
<script>
import { computed, reactive, ref } from "vue";
import store from "@/store";
import notification from "../NOTI/DeleteVue.vue";
export default {
  components: {
    basenotification: notification,
  },
  setup() {
    store.dispatch("md_trademark/set_all_trademarks");
    store.dispatch("md_typechair/get_typechair");
    const chonse_type = reactive({
      id_typechair: null,
      quality: null,
    });
    const erro = reactive({
      name: null,
      trademark: null,
      chair: null,
      number: null,
    });
    const validate = () => {
      store.state.md_airplane.update_airplane.name == null
        ? (erro.name = "không bỏ trống")
        : (erro.name = null);
      store.state.md_airplane.update_airplane.id_trademark == null
        ? (erro.trademark = "không bỏ trống")
        : (erro.trademark = null);
      chonse_type.quality == null
        ? (erro.number = "không bỏ trống")
        : (erro.number = null);
      chonse_type.id_typechair == null
        ? (erro.chair = "không bỏ trống")
        : (erro.chair = null);
    };
    const validate_lv2 = () => {
      chonse_type.quality < 0
        ? (erro.number = " Không hợp lệ ")
        : (erro.number = null);
    };
    let test = true;
    const adddata = () => {
      const data = {
        idtype: chonse_type.id_typechair,
        size: chonse_type.quality,
      };
      if (chonse_type.quality <= 0 || chonse_type.quality > 50) {
        errom.status = true;
        errom.mess = " Vui lòng nhập số lượng ";
      } else if (chonse_type.id_typechair == null) {
        errom.status = true;
        errom.mess = "Vui lòng nhập Loại ghế ";
      } else {
        test = true;
        checkincludes();
        if (test == false) {
          errom.status = true;
          errom.mess = "Trùng loại";
        } else {
          store.state.md_airplane.update_airplane.array_type.push(data);
        }
      }
    };
    const checkincludes = () => {
      for (var x in store.state.md_airplane.update_airplane.array_type) {
        if (
          store.state.md_airplane.update_airplane.array_type[x].idtype ==
          chonse_type.id_typechair
        ) {
          test = false;
        }
      }
    };
    const status = ref(false);
    const list_trademark = computed(() => {
      return store.state.md_trademark.trademark;
    });

    const list_typechair = computed(() => {
      return store.state.md_typechair.typechair;
    });
    const errom = reactive({
      status: false,
      mess: "",
    });
    const onsubmit = () => {
      validate();
      if (
        erro.name != null ||
        erro.number != null ||
        erro.trademark != null ||
        erro.chair != null
      ) {
        console.log("fail");
      } else {
        validate_lv2();
        if (
          erro.name != null ||
          erro.number != null ||
          erro.trademark != null ||
          erro.chair != null
        ) {
          console.log("fail lv2");
        } else {
          if (store.state.md_airplane.update_airplane.statuschonse == null) {
            if (
              store.state.md_airplane.update_airplane.array_type.length == 0
            ) {
              errom.status = true;
              errom.mess = "Hãy Nhập ghế !";
            } else {
              store.dispatch(
                "md_airplane/post_airplane",
                store.state.md_airplane.update_airplane
              );
              store.state.md_airplane.mess_airplane = null;
              setTimeout(() => {
                store.state.md_airplane.mess_airplane =
                  "Airplane Add success !";
              }, 100);
            }
          } else {
            if (
              store.state.md_airplane.update_airplane.array_type.length == 0
            ) {
              errom.status = true;
              errom.mess = "Hãy Nhập ghế !";
            } else {
              store.dispatch(
                "md_airplane/update_airplane",
                store.state.md_airplane.update_airplane
              );
              store.state.md_airplane.mess_airplane = null;
              setTimeout(() => {
                store.state.md_airplane.mess_airplane =
                  " Airplane Update success !";
              }, 100);
            }
          }
        }
      }
    };
    const reset = () => {
      store.state.md_airplane.update_airplane.array_type = [];
      store.state.md_airplane.update_airplane.statuschonse = null;
      store.state.md_airplane.update_airplane.id_trademark = null;
      store.state.md_airplane.update_airplane.id = null;
      store.state.md_airplane.update_airplane.name = null;
    };
    const update = () => {
      console.log(store.state.md_airplane.update_airplane);
      store.dispatch(
        "post_airplane",
        store.state.md_airplane.update_airplane.array_type
      );
    };
    return {
      list_trademark,
      status,
      list_typechair,
      chonse_type,
      adddata,
      onsubmit,
      update,
      erro,
      errom,
      reset,
    };
  },
};
</script>
<style scoped>
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
  z-index: 10;
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
/* //erro  */
.check {
  text-align: center;
  gap: 10px;
  display: flex;
  flex-direction: column;
}
.erro {
  color: red;
  font-size: 13px;
}
/* erro */
.ADD,
.UPDATE,
.RELOAD {
  display: flex;
  align-items: flex-end;
}
.addchair {
  display: flex;
  align-items: flex-end;
}
.addchair > button {
  color: white;
}
.top {
  display: flex;
  gap: 10px;
  padding: 10px;
}
.bottom {
  padding: 20px;
  background: rgb(197, 197, 197);
  position: relative;
}
.item {
  background: white;
  padding: 10px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  border-radius: 10px;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.498);
}
.icon {
  position: absolute;
  top: 5px;
  right: 5px;
  font-size: 20px;
}
.bottom {
  display: flex;
  padding: 20px;
  background: rgb(231, 228, 228);
  display: flex;
  gap: 10px;
}
.push,
.create,
.update,
.reload {
  background: blue;
  padding: 15px 20px;
  border: none;
  font-weight: 600;
  border-radius: 10px;
}
.create {
  background: rgb(88, 202, 88);
}
.reload {
  background: rgb(140, 140, 199);
}
.update {
  background: rgb(255, 255, 12);
  color: black;
}
.crud {
  justify-content: space-around;
  z-index: 8;
  background: white;
  padding: 10px;
  font-weight: 500;
}
.name,
.trademark,
.typechair,
.quality {
  display: flex;
  flex-direction: column;
}

input,
select {
  padding: 10px;
}
label {
  font-weight: 600;
  margin: 5px;
}
</style>
