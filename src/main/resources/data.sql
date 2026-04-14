-- 1. Insert some Roasters first (since Beans depend on them)
INSERT INTO roasters (name, location, year_established, deleted_at)
VALUES
    ('Blue Bottle', 'Oakland, USA', 2002, NULL),
    ('Stumptown', 'Portland, USA', 1999, NULL),
    ('Onyx Coffee Lab', 'Arkansas, USA', 2012, NULL);

-- 2. Insert Coffee Beans linked to those Roasters
-- We assume the IDs generated above are 1, 2, and 3.
INSERT INTO coffee_beans (name, origin, roast_level, price_per_kg, stock_quantity, roaster_id, deleted_at)
VALUES
    ('Bella Donovan', 'Ethiopia/Sumatra', 'MEDIUM', 25.50, 100, 1, NULL),
    ('Giant Steps', 'Sumatra', 'DARK', 24.00, 50, 1, NULL),
    ('Holler Mountain', 'South America', 'LIGHT', 28.00, 75, 2, NULL),
    ('Hair Bender', 'Multi-Region', 'MEDIUM', 26.00, 120, 2, NULL),
    ('Southern Weather', 'Ethiopia/Colombia', 'LIGHT', 32.00, 40, 3, NULL);

-- 3. (Optional) Insert one "soft-deleted" bean to test your repository filters
INSERT INTO coffee_beans (name, origin, roast_level, price_per_kg, stock_quantity, roaster_id, deleted_at)
VALUES
    ('Old Burnt Batch', 'Unknown', 'DARK', 5.00, 10, 1, NOW());