INSERT INTO inventory_items (name, sku, quantity, reorder_threshold, last_updated, supplier)
VALUES
  ('Rice 5kg', 'RICE-001', 12, 15, CURRENT_TIMESTAMP, 'AgroMart'),
  ('Cooking Oil 1L', 'OIL-101', 4, 10, CURRENT_TIMESTAMP, 'FoodBros'),
  ('Salt', 'SALT-201', 22, 10, CURRENT_TIMESTAMP, 'SpiceUp'),
  ('Wheat Flour 10kg', 'FLOUR-701', 8, 20, CURRENT_TIMESTAMP, 'FarmFresh'),
  ('Sugar', 'SUGAR-300', 18, 18, CURRENT_TIMESTAMP, 'SweetMart');