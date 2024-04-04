package domain.contract.action;

public interface UseCase<A, R> {

    R execute(A action);

}
