import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaxiParkTest {

    @Test
    void calculateTotalCost() {
        // Создаем несколько легковых автомобилей
        Sedan sedan1 = new Sedan("Toyota", 8.5, 12000, 180);
        Sedan sedan2 = new Sedan("Honda", 7.8, 15000, 200);
        Sedan sedan3 = new Sedan("Ford", 9.2, 11000, 170);

        // Создаем список автомобилей и передаем его в таксопарк
        List<Car> carList = Arrays.asList(sedan1, sedan2, sedan3);
        TaxiPark taxiPark = new TaxiPark(carList);

        // Рассчитываем общую стоимость и проверяем, что она соответствует ожидаемому значению
        double totalCost = taxiPark.calculateTotalCost();
        assertEquals(38000.0, totalCost);
    }

    @Test
    void sortByFuelConsumption() {
        // Создаем несколько легковых автомобилей с разными витратами топлива
        Sedan sedan1 = new Sedan("Toyota", 8.5, 12000, 180);
        Sedan sedan2 = new Sedan("Honda", 7.8, 15000, 200);
        Sedan sedan3 = new Sedan("Ford", 9.2, 11000, 170);

        // Создаем список автомобилей и передаем его в таксопарк
        List<Car> carList = Arrays.asList(sedan1, sedan2, sedan3);
        TaxiPark taxiPark = new TaxiPark(carList);

        // Сортируем автомобили по витратам топлива
        taxiPark.sortByFuelConsumption();

        // Проверяем, что автомобили отсортированы в правильном порядке
        assertEquals(sedan2, taxiPark.getCars().get(0));  // Самый экономичный
        assertEquals(sedan1, taxiPark.getCars().get(1));
        assertEquals(sedan3, taxiPark.getCars().get(2));  // Самый неэкономичный
    }

    @Test
    void findCarBySpeedRange() {
        // Создаем несколько легковых автомобилей с разными максимальными скоростями
        Sedan sedan1 = new Sedan("Toyota", 8.5, 12000, 180);
        Sedan sedan2 = new Sedan("Honda", 7.8, 15000, 200);
        Sedan sedan3 = new Sedan("Ford", 9.2, 11000, 170);

        // Создаем список автомобилей и передаем его в таксопарк
        List<Car> carList = Arrays.asList(sedan1, sedan2, sedan3);
        TaxiPark taxiPark = new TaxiPark(carList);

        // Поиск автомобиля в заданном диапазоне скорости
        Car carInSpeedRange = taxiPark.findCarBySpeedRange(180, 190);
        assertNotNull(carInSpeedRange);
        assertEquals("Toyota", carInSpeedRange.getBrand());
        assertEquals(8.5, ((Sedan) carInSpeedRange).getFuelConsumption());
        assertEquals(12000.0, carInSpeedRange.getCost());
        assertEquals(180, ((Sedan) carInSpeedRange).getMaxSpeed());

        // Если нет машин в заданном диапазоне, должен вернуться null
        Car carNotInSpeedRange = taxiPark.findCarBySpeedRange(220, 250);
        assertNull(carNotInSpeedRange);
    }
}
