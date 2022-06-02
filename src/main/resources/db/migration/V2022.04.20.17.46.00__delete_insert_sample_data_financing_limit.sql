DELETE FROM flyway_schema_history
WHERE description = 'inset sample data to financing limit transaction'
  AND EXISTS(SELECT 1 FROM flyway_schema_history  WHERE description = 'inset sample data to financing limit transaction' LIMIT 1)