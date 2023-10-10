<template>
  <div class="mainprofile">
    <div class="top">
      <div class="logo">
        <span class="img">
          <font-awesome-icon :icon="['fas', 'user-clock']" />
        </span>
        <h4>{{ data.fullname }}</h4>
      </div>
    </div>
    <div class="table">
      <table id="customers">
        <tr>
          <th>Email</th>
          <td>{{ data.email }}</td>
        </tr>
        <tr>
          <th>Age</th>
          <td>{{ data.age }}</td>
        </tr>
        <tr>
          <th>Passport</th>
          <td>{{ data.passport }}</td>
        </tr>
        <tr>
          <th>nationlity</th>
          <td>{{ data.nationality }}</td>
        </tr>
        <tr>
          <th>Flight</th>
          <td>
            {{ data.namelocation_departure }} -
            {{ data.namelocation_complete }} ({{
              converttime(data.date_departure)
            }})
          </td>
        </tr>
        <tr>
          <th>Payment</th>
          <td>
            {{ data.pricetick }} vnd (ticker) - {{ data.pricechair }} vnd
            (chair)
          </td>
        </tr>
        <tr>
          <th>Satus</th>
          <td>{{ data.status }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { computed } from "vue";
import { useRoute } from "vue-router";
export default {
  setup() {
    store.dispatch("md_customer/get_customer");
    const data = computed(() => {
      return store.getters["md_customer/getcustomerid"](
        Number(useRoute().params.id)
      );
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
    return {
      data,
      converttime,
    };
  },
};
</script>
<style scoped>
/* tabel */
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
  padding-bottom: 12rgb (0, 179, 113);
  text-align: left;
  background-color: #f8f8f8;
  color: rgb(0, 0, 0);
}
.table {
  padding: 10px 0;
}
/* end table */
.mainprofile {
  margin: 20px;
  background: white;
  padding: 20px;
}
.top {
  display: flex;
}
.logo {
  display: flex;
  padding: 10px;
  gap: 20px;
  border-radius: 10px;
  box-shadow: 0 0 4px rgba(78, 77, 77, 0.468);
}
.img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background: blue;
  display: flex;
  color: white;
  font-size: 20px;
  justify-content: center;
  align-items: center;
}
</style>
