package domain.contract;

/**
 * Engine to execute action or queries
 */
public interface Engine {

   <A, T> T execute(A action);
}
