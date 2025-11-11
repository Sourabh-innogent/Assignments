import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  orders: []
};

const ordersSlice = createSlice({
  name: "orders",
  initialState,
  reducers: {
    addOrder: (state, action) => {
      const order = {
        id: Date.now(),
        items: action.payload.items,
        address: action.payload.address,
        total: action.payload.total,
        status: "Pending",
        orderDate: new Date().toISOString(),
        deliveryTime: new Date(Date.now() + 6 * 60 * 60 * 1000).toISOString() // 6 hours
      };
      state.orders.unshift(order);
    },
    
    updateOrderStatus: (state, action) => {
      const order = state.orders.find(o => o.id === action.payload.id);
      if (order) {
        order.status = action.payload.status;
      }
    }
  }
});

export const { addOrder, updateOrderStatus } = ordersSlice.actions;
export default ordersSlice.reducer;
