<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div class="ADDS" v-if="!$store.state.md_location.data.id">
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create
      </button>
    </div>
    <div
      v-if="
        $store.state.md_location.data.status || $store.state.md_location.data.id
      "
      class="UPDATE"
    >
      <button class="update" type="button" @click="update">Update</button>
      <button type="button" @click="reset" class="reload">
        <font-awesome-icon :icon="['fas', 'repeat']" />
      </button>
    </div>
    <div class="name">
      <label>Tên địa điểm {{ $store.state.md_location.data.img }} </label>
      <div class="check">
        <input
          placeholder="Nhập ..."
          :style="
            $store.state.md_location.erro.name == null
              ? null
              : { border: '1px solid red' }
          "
          v-model="$store.state.md_location.data.name"
          type="text"
          name=""
        />
        <span v-if="$store.state.md_location.erro.name" class="erro">{{
          $store.state.md_location.erro.name
        }}</span>
      </div>
    </div>
    <div class="contry">
      <label>Tên quốc gia</label>
      <div class="check">
        <input
          placeholder="Nhập ..."
          :style="
            $store.state.md_location.erro.contry == null
              ? null
              : { border: '1px solid red' }
          "
          v-model="$store.state.md_location.data.contry"
          type="text"
          name=""
        />
        <span v-if="$store.state.md_location.erro.contry" class="erro">{{
          $store.state.md_location.erro.contry
        }}</span>
      </div>
    </div>
    <div class="image">
      <img
        width="50"
        height="50"
        v-if="data.url != null || $store.state.md_location.data.status"
        :src="
          $store.state.md_location.data.status
            ? 'http://localhost:8081/img/location/' +
              $store.state.md_location.data.id +
              '&3&' +
              $store.state.md_location.data.img
            : data.url
        "
      />
      <div class="check">
        <label for="">Chọn hình ảnh</label>
        <div class="ipimg">
          <label class="requid">* jpg,png</label>
          <input
            :style="
              $store.state.md_location.erro.img == null
                ? null
                : { border: '1px solid red' }
            "
            id="file"
            type="file"
            name="file"
            multiple
            @change="showonsubmit"
          />
        </div>
        <span v-if="$store.state.md_location.erro.img" class="erro">{{
          $store.state.md_location.erro.img
        }}</span>
      </div>
    </div>
  </form>
</template>
<script>
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    store.state.md_location.mess_location = null;
    const data = reactive({
      url: null,
      file: null,
    });

    const validate = () => {
      !store.state.md_location.data.name
        ? (store.state.md_location.erro.name = "không bỏ trống")
        : (store.state.md_location.erro.name = null);
      !store.state.md_location.data.contry
        ? (store.state.md_location.erro.contry = "không bỏ trống")
        : (store.state.md_location.erro.contry = null);
      !store.state.md_location.data.img
        ? (store.state.md_location.erro.img = "không bỏ trống")
        : (store.state.md_location.erro.img = null);
    };

    const onsubmit = () => {
      validate();
      data.file != null
        ? ((store.state.md_location.data.img = data.file.name),
          (store.state.md_location.erro.img = null))
        : null;
      store.state.md_location.erro.name != null ||
      store.state.md_location.erro.img != null ||
      store.state.md_location.erro.contry != null
        ? console.log("fail")
        : ((store.state.md_location.mess_location = null),
          store.dispatch("md_location/post_location"));
    };

    const showonsubmit = (event) => {
      const file = event.target.files;
      if (
        file[0].type.substring(6, 9) != "jpe" &&
        file[0].type.substring(6, 9) != "png"
      ) {
        store.state.md_location.erro.img = "Không đúng định dạng";
      } else {
        store.state.md_location.data.status = false;
        data.file = file[0];
        store.state.md_location.data.img = data.file.name;
        store.state.md_location.file_img = data.file;
        store.state.md_location.erro.img = null;
        data.url = URL.createObjectURL(file[0]);
      }
    };
    const reset = () => {
      store.state.md_location.data.status = false;
      store.state.md_location.data.id = 0;
      store.state.md_location.data.img = null;
    };
    const update = () => {
      validate();
      data.file != null
        ? ((store.state.md_location.data.img = data.file.name),
          (store.state.md_location.erro.img = null))
        : null;

      store.state.md_location.erro.name != null ||
      store.state.md_location.erro.img != null ||
      store.state.md_location.erro.contry != null
        ? console.log("update fail")
        : data.file != null
        ? ((store.state.md_location.mess_location = null),
          store.dispatch("md_location/post_location"))
        : ((store.state.md_location.mess_location = null),
          store.dispatch("md_location/put_location"));
    };
    return {
      update,
      onsubmit,
      data,
      showonsubmit,
      location,
      reset,
    };
  },
};
</script>
<style scoped>
.image {
  display: flex !important;
  flex-direction: row !important;
  gap: 10px;
  align-items: flex-end !important;
}
.UPDATE {
  flex-direction: row !important;
  gap: 10px;
  align-items: flex-end !important;
}
.UPDATE > button {
  padding: 10px 30px;
  border-radius: 10px;
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
.ipimg {
  position: relative;
}
.requid {
  position: absolute;
  top: 100%;
  font-weight: 500;
}
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
  justify-content: space-between;
  padding: 30px;
}
.ADDS {
  flex-direction: row !important;
  gap: 10px;
  align-items: flex-end !important;
}
.ADD {
  background: rgb(88, 202, 88);
  font-weight: 500;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 4px;
  font-weight: 600;
  white-space: nowrap;
}
.crud > div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
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
  margin-bottom: 5px;
}
</style>
