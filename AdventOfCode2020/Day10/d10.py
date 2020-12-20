from collections import defaultdict

if __name__ == "__main__":
    with open("input.txt") as fin:
        adapters = sorted(map(int, fin))

    # part 1
    diff_dist = defaultdict(int)
    diff_dist[adapters[0]] = 1
    for i in range(1, len(adapters)):
        diff_dist[adapters[i] - adapters[i - 1]] += 1
    diff_dist[3] += 1

    print(diff_dist[1] * diff_dist[3])

    # part 2
    adapters.insert(0, 0)
    adapters.append(adapters[-1] + 3)
    size = len(adapters)

    combos_up_to_index = [1] * size
    for i in range(1, size):
        combos_up_to_index[i] = combos_up_to_index[i - 1]
        if i > 1 and adapters[i] - adapters[i - 2] <= 3:
            combos_up_to_index[i] += combos_up_to_index[i - 2]
        if i > 2 and adapters[i] - adapters[ i - 3] <= 3:
            combos_up_to_index[i] += combos_up_to_index[i - 3]

    print(combos_up_to_index[-1])