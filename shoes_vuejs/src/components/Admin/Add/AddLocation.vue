<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div>
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create New
      </button>
    </div>
    <div class="name">
      <label for="">Tên địa điểm </label>
      <div class="check">
        <input
          placeholder="Nhập ..."
          :style="erro.name == null ? null : { border: '1px solid red' }"
          v-model="location.name"
          type="text"
          name=""
        />
        <span v-if="erro.name" class="erro">{{ erro.name }}</span>
      </div>
    </div>
    <div class="contry">
      <label for="">Tên quốc gia</label>
      <div class="check">
        <input
          placeholder="Nhập ..."
          :style="erro.contry == null ? null : { border: '1px solid red' }"
          v-model="location.contry"
          type="text"
          name=""
        />
        <span v-if="erro.contry" class="erro">{{ erro.contry }}</span>
      </div>
    </div>
    <div class="image">
      <img width="50" height="50" v-if="data.url != null" :src="data.url" />
      <div class="check">
        <input
          :style="erro.img == null ? null : { border: '1px solid red' }"
          id="file"
          type="file"
          name="file"
          multiple
          @change="showonsubmit"
        />
        <span v-if="erro.img" class="erro">{{ erro.img }}</span>
      </div>
    </div>
  </form>
</template>
<script>
import { getStorage, ref, uploadBytes } from "firebase/storage";
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    store.state.md_location.mess_location = null;
    const data = reactive({
      url: null,
      file: null,
    });
    const location = reactive({
      name: null,
      contry: null,
      img: null,
    });
    const erro = reactive({
      name: null,
      contry: null,
      img: null,
    });

    const validate = () => {
      location.name == null
        ? (erro.name = "không bỏ trống")
        : (erro.name = null);
      location.contry == null
        ? (erro.contry = "không bỏ trống")
        : (erro.contry = null);
      location.img == null ? (erro.img = "không bỏ trống") : (erro.img = null);
    };
    const storage = getStorage();
    const onsubmit = () => {
      validate();
      if (data.file != null) {
        location.img = data.file.name;
        erro.img = null;
      }
      if (erro.name != null || erro.img != null || erro.contry != null) {
        console.log("fail");
      } else {
        const storageRef = ref(storage, "location/" + data.file.name);
        uploadBytes(storageRef, data.file);

        store.dispatch("md_location/post_location", location);

        store.state.md_location.mess_location = null;
        setTimeout(() => {
          store.state.md_location.mess_location = "Location Add success !";
        }, 1000);
      }
    };
    const showonsubmit = (event) => {
      const file = event.target.files;
      data.file = file[0];
      data.url = URL.createObjectURL(file[0]);
    };
    return {
      onsubmit,
      data,
      showonsubmit,
      location,
      erro,
    };
  },
};
</script>
<style scoped>
.check {
  display: flex;
  flex-direction: column;
  padding: 20px;
  position: relative;
}
.erro {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  color: red;
  font-size: 13px;
}
.crud {
  display: flex;
  background: white;
  justify-content: space-between;
  padding: 10px;
}
.ADD {
  background: rgb(88, 202, 88);
  font-weight: 500;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 10px;
  font-weight: 600;
}
.crud > div {
  display: flex;
  align-items: center;
  gap: 10px;
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
</style>
