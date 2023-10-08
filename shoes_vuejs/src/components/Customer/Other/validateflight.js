import router from "@/router";
import store from "@/store";

export default () => {
  const checknull = () => {
    if (
      store.state.list.search.round_trip == true ||
      store.state.list.search.round_trip == "true"
    ) {
      if (
        !store.state.list.search.exits_chair ||
        !store.state.list.search.from ||
        !store.state.list.search.to ||
        !store.state.list.search.date ||
        !store.state.list.search.Dates_to
      ) {
        return false;
      }
      return true;
    } else {
      if (
        !store.state.list.search.exits_chair ||
        !store.state.list.search.from ||
        !store.state.list.search.to ||
        !store.state.list.search.date
      ) {
        return false;
      }
      return true;
    }
  };

  const onsubmit = () => {
    store.state.my_tick_none.id_tick = [];
    store.state.list.search.select = null;
    if (!checknull()) {
      store.state.hasNotifi = true;
    } else if (
      store.state.list.search.round_trip == "true" ||
      store.state.list.search.round_trip == true
    ) {
      if (!store.state.list.search.date) {
        store.state.hasNotifi = true;
      } else {
        store.state.search.filters = false;
        store.state.search.status = true;
        //buff touter
        router.push({
          name: "search",
          query: {
            from: store.state.list.search.from,
            to: store.state.list.search.to,
            chair: store.state.list.search.exits_chair,
            date: store.state.list.search.date,
            round_trip: true,
            date2: store.state.list.search.Dates_to,
          },
        });
      }
    } else {
      store.state.search.filters = false;
      store.state.search.status = true;
      router.push({
        //buff touter
        name: "search",
        query: {
          from: store.state.list.search.from,
          to: store.state.list.search.to,
          chair: store.state.list.search.exits_chair,
          date: store.state.list.search.date,
          // check khu hoi
          round_trip: false,
        },
      });
    }
  };
  return {
    onsubmit,
  };
};
