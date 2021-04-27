

def import_embedding_model(): #word2vec model

    from gensim.models import KeyedVectors
    import pickle
    word2vec_model = KeyedVectors.load('/data/data/com.example.barcode/files/chaquopy/AssetFinder/app/Data/kowiki_word2vec_wordvectors')#embedding model import
    print("word2vec model import")

    return word2vec_model

def import_recipe_vector(): #recipe_vector
    import pickle

    document_embedding_list = []
    with open("/data/data/com.example.barcode/files/chaquopy/AssetFinder/app/Data/document_embedding_list", "rb") as file: #recipe vector import
        temp = pickle.load(file)
        document_embedding_list = temp

    print("recipe_vec import ")

    return document_embedding_list


#상품명 평균백터 구하기
def vectors_pn(document_list):
    document_embedding_list = []
    docs_vec =None
    word2vec_model = import_embedding_model() #word2vec model import
    count = 0
    #각 명사의 백터를 구한 뒤 더함
    for i in range(len(document_list)):
        index = 0
        weight_token = 0
        for j in range(len(document_list[i])):
            index = len(document_list[i])
            weight_token = 1 / index
            count += 1
            #리스트의 모든 값을 불러와서 백터값을 구함
            try:
                if docs_vec is None:
                    docs_vec = word2vec_model[document_list[i][j]] * weight_token * (count)
                else:
                    docs_vec = docs_vec + word2vec_model[document_list[i][j]] * weight_token * (count)
            except KeyError as e:
                print(e)

        #더한 명사의 양으로 나눔
        if docs_vec is not None:
            docs_vec = docs_vec / count
            #나눈 값을 저장 후 변수 초기화
            document_embedding_list.append(docs_vec)
            count = 0
            docs_vec = None
    return document_embedding_list

def get_instance(*input):
    merged_data = []
    merged_data.append(input)
    print(merged_data)
    return merged_data

#백터끼리의 유사도 계산
def compute_similarity(*input_list):
    #각 백터끼리의 코사인 유사도를 구함
    res_string = ""
    temp = input_list
    document_embedding_list = import_recipe_vector() #recipe_vector
    input_list_vec = vectors_pn(temp) #input_words

    from sklearn.metrics.pairwise import cosine_similarity
    cosine_similarities = cosine_similarity(input_list_vec, document_embedding_list)

    #상품명과 유사한 상위 5개 레시피 추출s
    sim_scores = list(enumerate(cosine_similarities[0]))###temp_a 쓸때 index = 0 아니면 pn_num
    sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse = True)
    sim_scores = sim_scores[0:10]#출력할 레시피 수 지정
    #결과 확인
    result = []
    print()
    for i in range(10):
        res_string = res_string + str(sim_scores[i][0]) + "/"
    return res_string