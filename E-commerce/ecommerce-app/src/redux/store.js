import { configureStore } from "@reduxjs/toolkit";
import cartReducer from "./cartSlice";
import ordersReducer from "./ordersSlice";
import { loadState, saveState } from "../utils/localStorage";

const persistedState = loadState();

const store = configureStore({
  reducer: {
    cart: cartReducer,
    orders: ordersReducer,
  },
  preloadedState: persistedState
});

store.subscribe(() => {
  saveState({
    cart: store.getState().cart,
    orders: store.getState().orders
  });
});

export default store;
