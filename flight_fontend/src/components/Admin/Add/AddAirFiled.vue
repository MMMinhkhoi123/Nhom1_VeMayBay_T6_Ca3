<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div class="sett" v-if="!$store.state.md_airfield.data.id">
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create
      </button>
    </div>
    <div v-if="$store.state.md_airfield.data.id" class="UPDATE sett">
      <button class="update" type="submit">Update</button>
      <button type="button" @click="reset" class="reload">
        <font-awesome-icon :icon="['fas', 'repeat']" />
      </button>
    </div>
    <div class="code">
      <label>Tên Sân Bay {{ $store.state.md_airfield.data.contry }}</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.name == null ? null : { border: '1px solid red' }"
          v-model="$store.state.md_airfield.data.name"
          type="text"
          name=""
        />
        <span v-if="erro.name" class="erro">{{ erro.name }}</span>
      </div>
    </div>
    <div>
      <label>Địa chỉ</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.adrress == null ? null : { border: '1px solid red' }"
          v-model="$store.state.md_airfield.data.adrress"
          type="text"
          name=""
        />
        <span v-if="erro.adrress" class="erro">{{ erro.adrress }}</span>
      </div>
    </div>
    <div class="contry">
      <label>Tên quốc gia</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.contry == null ? null : { border: '1px solid red' }"
          v-model="$store.state.md_airfield.data.contry"
          type="text"
          name=""
        />
        <span v-if="erro.contry" class="erro">{{ erro.contry }}</span>
      </div>
    </div>
  </form>
</template>
<script>
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    const erro = reactive({
      name: null,
      adrress: null,
      contry: null,
    });
    const validate = () => {
      store.state.md_airfield.data.name == null ||
      store.state.md_airfield.data.name == ""
        ? (erro.name = "không bỏ trống")
        : (erro.name = null);
      store.state.md_airfield.data.adrress == null ||
      store.state.md_airfield.data.adrress == ""
        ? (erro.adrress = "không bỏ trống")
        : (erro.adrress = null);
      store.state.md_airfield.data.contry == null ||
      store.state.md_airfield.data.contry == ""
        ? (erro.contry = "không bỏ trống")
        : (erro.contry = null);
    };
    const reset = () => {
      store.state.md_airfield.data.id = 0;
    };
    const onsubmit = () => {
      validate();
      erro.name != null || erro.adrress != null || erro.contry != null
        ? console.log("fail")
        : ((store.state.md_airfield.mess_airfield = null),
          store.dispatch("md_airfield/post_airfield"));
    };
    return {
      onsubmit,
      location,
      erro,
      reset,
    };
  },
};
</script>
<style scoped>
.UPDATE > button {
  padding: 10px 30px;
  border-radius: 4px;
  font-weight: 700;
}
.UPDATE > button:nth-child(1) {
  border: none;
  background: rgb(228, 199, 14);
}
.UPDATE > button:nth-child(2) {
  border: none;
  background: rgba(87, 86, 88, 0.509);
}
/* //check */
.check {
  display: flex;
  flex-direction: column;
  position: relative;
}
.erro {
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translateX(-50%);
  color: red;
  font-size: 13px;
}
.crud {
  display: flex;
  background: white;
  padding: 20px;
  justify-content: space-between;
}
.ADD {
  background: rgb(88, 202, 88);
  font-weight: 500;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 4px;
  font-weight: 600;
}
.crud > div {
  display: flex;
  flex-direction: column;
  align-items: center;
}
input {
  padding: 10px 20px;
  border-radius: 5px;
  outline: none;
  border: 1px solid gray;
}
img {
  border-radius: 50%;
}
i {
  pointer-events: none;
}
label {
  font-weight: 700;
}
.sett {
  display: flex;
  flex-direction: row !important;
  gap: 10px;
  justify-content: end !important;
  align-items: flex-end !important;
}
</style>
