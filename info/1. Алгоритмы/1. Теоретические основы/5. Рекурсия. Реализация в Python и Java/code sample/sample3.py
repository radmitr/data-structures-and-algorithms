base_list = [1, [2, 3], [4, 5, [6, 7]], [8, [9]], 10]


def get_element(base_list, result_list=None):
    if result_list is None:
        result_list = []
    for element in base_list:
        if type(element) == list:
            get_element(element, result_list)
        else:
            result_list.append(element)
    return result_list


result_list = get_element(base_list)

print(result_list)