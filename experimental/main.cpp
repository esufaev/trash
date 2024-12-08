#include <iostream>
#include <atomic>
#include <thread>
#include <chrono>
#include <cassert>
#include "thread_pool_executor.h"

using namespace pot::executors;

pot::coroutines::task<int> async_computation(int value)
{
    std::cout << "Starting coroutine..." << std::endl;
    std::this_thread::sleep_for(std::chrono::seconds(2));

    int result = value * 2;
    std::cout << "Coroutine finished with result: " << result << std::endl;
    co_return result;
}

int main()
{
    thread_pool_executor<true> executor("GlobalQueueExecutor", 4);

    auto result_task = executor.run([]()
                                    { return async_computation(20); });

    // int result = result_task.get();

    // assert(result == 40);
    // std::cout << "Result: " << result << std::endl;

    executor.shutdown();
    return 0;
}
