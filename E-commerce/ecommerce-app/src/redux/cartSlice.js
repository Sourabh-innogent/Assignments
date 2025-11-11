import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  cartItems: [],
  totalQuantity: 0,
  totalAmount: 0,
  promocode: null,
  discount: 0,
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addToCart: (state, action) => {
      const item = action.payload;
      const existingItem = state.cartItems.find(p => p.id === item.id);
      
      if (existingItem) {
        existingItem.quantity += 1;
      } else {
        state.cartItems.push({ ...item, quantity: 1 });
      }

      state.totalQuantity += 1;
      state.totalAmount += item.price;
    },

    removeFromCart: (state, action) => {
      const itemId = action.payload;
      const item = state.cartItems.find(p => p.id === itemId);
      if (item) {
        state.totalQuantity -= item.quantity;
        state.totalAmount -= item.price * item.quantity;
        state.cartItems = state.cartItems.filter(p => p.id !== itemId);
      }
    },

    increaseQuantity: (state, action) => {
      const item = state.cartItems.find(p => p.id === action.payload);
      if (item) {
        item.quantity++;
        state.totalQuantity++;
        state.totalAmount += item.price;
      }
    },

    decreaseQuantity: (state, action) => {
      const item = state.cartItems.find(p => p.id === action.payload);
      if (item && item.quantity > 1) {
        item.quantity--;
        state.totalQuantity--;
        state.totalAmount -= item.price;
      }
    },

    clearCart: (state) => {
      state.cartItems = [];
      state.totalQuantity = 0;
       state.totalAmount = 0;
       state.promocode = null;
        state.discount = 0;
    },
    applyPromocode: (state, action) => {
        const { code, discount } = action.payload;
        state.promocode = code;
        state.discount = discount;
    },

    removePromocode: (state) => {
        state.promocode = null;
        state.discount = 0;
    },
  },
});

export const {
  addToCart,
  removeFromCart,
  increaseQuantity,
  decreaseQuantity,
  clearCart,
  applyPromocode,
  removePromocode
} = cartSlice.actions;

export default cartSlice.reducer;
