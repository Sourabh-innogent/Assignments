import numpy as np

def show_stats(products):
    try:
        if not products:
            print("No Items in Inventory.")
            return
        
        prices = np.array([p.price for p in products])
        stocks = np.array([p.stock for p in products])
        total_values = prices * stocks

        print(f"\n Inventory Statistics ")
        print(f"Average Price of Items : ₹{np.mean(prices):.2f}")
        print(f"Most Expensive Item    : ₹{np.max(prices):.2f}")
        print(f"Total Items in Stock   : {np.sum(stocks)}")

        print("\n Total Value per Product:")
        for p, val in zip(products, total_values):
            print(f"  {p.name}: ₹{val:.2f}")

       
        tag = input("\nEnter tag for Avg. Price and Total Value (e.g., 'clearance'): ").strip()
        if tag:
            tagged = np.array([p for p in products if tag.lower() in (t.lower() for t in p.tags)])
            if len(tagged) > 0:
                t_prices = np.array([p.price for p in tagged])
                t_values = np.array([p.price * p.stock for p in tagged])
                print(f"\n Stats for Tag '{tag}':")
                print(f"  Products Found : {len(tagged)}")
                print(f"  Average Price  : ₹{np.mean(t_prices):.2f}")
                print(f"  Total Value    : ₹{np.sum(t_values):.2f}")
            else:
                print(f"No products found with tag '{tag}'.")
                
    except Exception as e:
        print(f" Error: {e}")
