package fr.mossaab.security.repository;

import fr.mossaab.security.models.DailyMeal;
import fr.mossaab.security.models.DailyMealDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyMealDishRepository extends JpaRepository<DailyMealDish, Long>, JpaSpecificationExecutor<DailyMealDish> {
    @Query("select d from DailyMealDish d where d.dailyMeal.id = :dailyMealId")
    List<DailyMealDish> findAllByDailyMealId(@Param("dailyMealId") Long dailyMealId);

    @Query("select d from DailyMealDish d where d.dailyMeal = :dailyMeal")
    List<DailyMealDish> findByDailyMeal(@Param("dailyMeal") DailyMeal dailyMeal);

    @Query("select (count(d) > 0) from DailyMealDish d where d.dish.id = :dishId")
    boolean existsAllByDishId(@Param("dishId") Long dishId);
}